package com.genius.wasylews.converterlab.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.converterlab.presenter.HomePresenter;
import com.genius.wasylews.converterlab.view.BaseHomeView;
import com.genius.wasylews.converterlab.view.OrganizationsAdapter;
import com.genius.wasylews.device.notification.NotificationUtil;
import com.genius.wasylews.domain.model.Organization;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity implements BaseHomeView,
        OrganizationsAdapter.CardToolbarItemClickedListener {

    @Inject
    HomePresenter mPresenter;

    @Inject
    OrganizationsAdapter mAdapter;

    @Inject
    NotificationUtil mNotificationManager;

    @BindView(R.id.home_activity_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_text_view)
    TextView mProgressTextView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setMenuItemClickedListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(mPresenter::loadList);

        mPresenter.setView(this);
        mPresenter.loadList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_activity_search_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mPresenter.findOrganizations(s);
                return true;
            }
        });
        return true;
    }

    @Override
    public void showOrganizations(List<Organization> list) {
        mAdapter.setOrganizations(list);
    }

    @Override
    public void showProgress() {
        mProgressTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressTextView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mNotificationManager.showNotification(getString(R.string.database_update_notification));
    }

    @Override
    public boolean onItemClicked(@IdRes int buttonId, Organization organization) {
        switch (buttonId) {
            case R.id.card_toolbar_link:
                mPresenter.openOrganizationSite(organization);
                break;
            case R.id.card_toolbar_map:
                mPresenter.showOnMap(organization);
                break;
            case R.id.card_toolbar_phone:
                mPresenter.callOrganization(organization);
                break;
            case R.id.card_toolbar_details:
                mPresenter.showDetails(organization);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void openSite(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    public void makePhoneCall(String phone) {
        Intent phoneDialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        startActivity(phoneDialIntent);
    }

    @Override
    public void openMap(Organization organization) {
        Intent mapIntent = MapActivity.newIntent(this, organization);
        startActivity(mapIntent);
    }

    @Override
    public void showOrganizationDetails(Organization organization) {
        Intent detailsIntent = DetailsActivity.newIntent(this, organization);
        startActivity(detailsIntent);
    }
}
