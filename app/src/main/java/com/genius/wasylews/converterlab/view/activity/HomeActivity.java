package com.genius.wasylews.converterlab.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.converterlab.presenter.HomePresenter;
import com.genius.wasylews.converterlab.view.BaseHomeView;
import com.genius.wasylews.converterlab.view.OrganizationsAdapter;
import com.genius.wasylews.domain.model.Organization;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity implements BaseHomeView, OrganizationsAdapter.CardToolbarItemClickedListener {

    @Inject
    HomePresenter presenter;

    @Inject
    OrganizationsAdapter adapter;

    @BindView(R.id.home_activity_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_text_view)
    TextView mProgressTextView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        adapter.setMenuItemClickedListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(presenter::loadList);

        presenter.setView(this);
        presenter.loadList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_activity_search_menu, menu);
        return true;
    }

    @Override
    public void showOrganizations(List<Organization> list) {
        adapter.setOrganizations(list);
    }

    @Override
    public void showOrganizationDetails(Organization organization) {
    }

    @Override
    public void showProgress() {
        mProgressTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressTextView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void openSite(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    public boolean onItemClicked(MenuItem item, Organization organization) {
        switch (item.getItemId()) {
            case R.id.toolbar_link:
                presenter.openOrganizationSite(organization);
                break;
            default:
                return false;
        }
        return true;
    }
}
