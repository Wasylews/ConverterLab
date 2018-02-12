package com.genius.wasylews.converterlab.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.converterlab.presenter.DetailsPresenter;
import com.genius.wasylews.converterlab.view.BaseDetailsView;
import com.genius.wasylews.converterlab.view.OrganizationDetailsAdapter;
import com.genius.wasylews.converterlab.view.fragment.ShareDialogFragment;
import com.genius.wasylews.device.number.NumberFormatter;
import com.genius.wasylews.domain.model.Organization;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import jahirfiquitiva.libs.fabsmenu.FABsMenu;
import jahirfiquitiva.libs.fabsmenu.FABsMenuListener;
import jahirfiquitiva.libs.fabsmenu.TitleFAB;

public class DetailsActivity extends DaggerAppCompatActivity implements BaseDetailsView {

    private static final String EXTRA_ORGANIZATION_ID = "ORGANIZATION_ID";

    @Inject
    DetailsPresenter mPresenter;

    @Inject
    OrganizationDetailsAdapter mAdapter;

    @BindView(R.id.details_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.details_organization_name)
    TextView mOrganizationName;

    @BindView(R.id.details_organization_address)
    TextView mOrganizationAddress;

    @BindView(R.id.details_organization_phone)
    TextView mOrganizationPhone;

    @BindView(R.id.details_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.fab_menu)
    FABsMenu mFABsMenu;

    @BindView(R.id.fab_menu_link)
    TitleFAB mFabLink;

    @BindView(R.id.fab_menu_map)
    TitleFAB mFabMap;

    @BindView(R.id.fab_menu_phone)
    TitleFAB mFabPhone;

    private Organization mOrganization;

    public static Intent newIntent(Context context, Organization organization) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_ORGANIZATION_ID, organization.getId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        setupFabMenu();

        mPresenter.setView(this);

        String id = getIntent().getStringExtra(EXTRA_ORGANIZATION_ID);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.loadOrganization(id));
        mPresenter.loadOrganization(id);
    }

    private void setupFabMenu() {
        mFABsMenu.attachToRecyclerView(mRecyclerView);
        mFABsMenu.setMenuListener(new FABsMenuListener() {

            @Override
            public void onMenuExpanded(FABsMenu fabsMenu) {
                super.onMenuExpanded(fabsMenu);
                fabsMenu.setMenuButtonIcon(R.drawable.ic_action_menu_close);
            }

            @Override
            public void onMenuCollapsed(FABsMenu fabsMenu) {
                super.onMenuCollapsed(fabsMenu);
                fabsMenu.setMenuButtonIcon(R.drawable.ic_action_menu);
            }
        });

        mFabLink.setOnClickListener(view -> {
            mFABsMenu.collapseImmediately();
            mPresenter.openOrganizationSite(mOrganization);
        });

        mFabMap.setOnClickListener(view -> {
            mFABsMenu.collapseImmediately();
            mPresenter.showOrganizationOnMap(mOrganization);
        });

        mFabPhone.setOnClickListener(view -> {
            mFABsMenu.collapseImmediately();
            mPresenter.callOrganization(mOrganization);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_activity_share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                openShareDialog();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void openShareDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        ShareDialogFragment dialog = ShareDialogFragment.newInstance(mOrganization);
        dialog.show(fragmentManager, ShareDialogFragment.TAG);
    }

    @Override
    public void showOrganization(Organization organization) {
        mOrganization = organization;

        getSupportActionBar().setTitle(organization.getTitle());
        getSupportActionBar().setSubtitle(organization.getCity());

        mOrganizationName.setText(organization.getTitle());
        mOrganizationAddress.setText(organization.getAddress());

        try {
            String phone = NumberFormatter.format("(xxxx) xx-xx-xx", organization.getPhone());
            mOrganizationPhone.setText(phone);
        } catch (IllegalArgumentException e) {
            mOrganizationPhone.setText("-");
        }

        mAdapter.setCurrencyList(organization.getCurrencies());

        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void openLink(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    public void openMap(Organization organization) {
        Intent mapIntent = MapActivity.newIntent(this, organization);
        startActivity(mapIntent);
    }

    @Override
    public void makeCall(String phone) {
        Intent phoneDialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        startActivity(phoneDialIntent);
    }
}
