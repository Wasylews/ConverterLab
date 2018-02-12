package com.genius.wasylews.converterlab.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.device.number.NumberFormatter;
import com.genius.wasylews.domain.model.Organization;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.OrganizationViewHolder> {

    public interface CardToolbarItemClickedListener {
        boolean onItemClicked(@IdRes int buttonId, Organization organization);
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

        try {
            String phone = NumberFormatter.format("xxx xx xxx xx", item.getPhone());
            holder.organizationPhoneTextView.setText(phone);
        } catch (IllegalArgumentException e) {
            holder.organizationPhoneTextView.setText("-");
        }

        holder.organizationLocationTextView.setText(item.getAddress());

        final View.OnClickListener clickListener = view -> mMenuItemClickedListener.onItemClicked(view.getId(), item);
        holder.cardToolbarLinkButton.setOnClickListener(clickListener);
        holder.cardToolbarMapButton.setOnClickListener(clickListener);
        holder.cardToolbarPhoneButton.setOnClickListener(clickListener);
        holder.cardToolbarDetailsButton.setOnClickListener(clickListener);
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

        @BindView(R.id.card_toolbar_link)
        ImageButton cardToolbarLinkButton;

        @BindView(R.id.card_toolbar_map)
        ImageButton cardToolbarMapButton;

        @BindView(R.id.card_toolbar_phone)
        ImageButton cardToolbarPhoneButton;

        @BindView(R.id.card_toolbar_details)
        ImageButton cardToolbarDetailsButton;

        OrganizationViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
