package com.genius.wasylews.converterlab.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.domain.model.Organization;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.OrganizationViewHolder> {

    public interface CardToolbarItemClickedListener {
        boolean onItemClicked(MenuItem item, Organization organization);
    }

    private final LayoutInflater mInflater;
    private List<Organization> mOrganizations;
    private CardToolbarItemClickedListener mMenuItemClickedListener;

    @Inject
    public OrganizationsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mOrganizations = Collections.emptyList();
    }

    @Override
    public OrganizationsAdapter.OrganizationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_activity_row_layout, parent, false);
        return new OrganizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrganizationViewHolder holder, int position) {
        final Organization item = mOrganizations.get(position);

        holder.organizationNameTextView.setText(item.getTitle());
        holder.organizationRegionTextView.setText(item.getRegion());
        holder.organizationCityTextView.setText(item.getCity());
        holder.organizationPhoneTextView.setText(item.getPhone());
        holder.organizationLocationTextView.setText(item.getAddress());

        holder.toolbar.getMenu().clear();
        holder.toolbar.inflateMenu(R.menu.card_toolbar_menu);
        holder.toolbar.setOnMenuItemClickListener(menuItem -> mMenuItemClickedListener.onItemClicked(menuItem, item));
    }

    @Override
    public int getItemCount() {
        return mOrganizations.size();
    }

    public void setOrganizations(List<Organization> organizations) {
        mOrganizations = organizations;
        notifyDataSetChanged();
    }

    public void setMenuItemClickedListener(CardToolbarItemClickedListener menuItemClickedListener) {
        mMenuItemClickedListener = menuItemClickedListener;
    }

    class OrganizationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.organization_name)
        TextView organizationNameTextView;

        @BindView(R.id.organization_region)
        TextView organizationRegionTextView;

        @BindView(R.id.organization_city)
        TextView organizationCityTextView;

        @BindView(R.id.organization_phone)
        TextView organizationPhoneTextView;

        @BindView(R.id.organization_location)
        TextView organizationLocationTextView;

        @BindView(R.id.card_toolbar)
        Toolbar toolbar;

        OrganizationViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
