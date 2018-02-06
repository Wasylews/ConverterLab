package com.genius.wasylews.converterlab.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.domain.model.Currency;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizationDetailsAdapter extends RecyclerView.Adapter<OrganizationDetailsAdapter.DetailsViewHolder> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<Currency> mCurrencyList;

    @Inject
    public OrganizationDetailsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCurrencyList = Collections.emptyList();
    }

    public void setCurrencyList(List<Currency> currencyList) {
        mCurrencyList = currencyList;
        notifyDataSetChanged();
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.details_activity_currency_row_layout, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        Currency currency = mCurrencyList.get(position);

        holder.currencyName.setText(currency.getName());

        if (currency.isAskUp()) {
            holder.askStatusImage.setImageResource(R.drawable.ic_green_arrow_up);
            holder.askValueView.setTextColor(ContextCompat.getColor(mContext, R.color.colorGreen));
        } else {
            holder.askStatusImage.setImageResource(R.drawable.ic_red_arrow_down);
            holder.askValueView.setTextColor(ContextCompat.getColor(mContext, R.color.colorRed));
        }

        if (currency.isBidUp()) {
            holder.bidStatusImage.setImageResource(R.drawable.ic_green_arrow_up);
            holder.bidValueView.setTextColor(ContextCompat.getColor(mContext, R.color.colorGreen));
        } else {
            holder.bidStatusImage.setImageResource(R.drawable.ic_red_arrow_down);
            holder.bidValueView.setTextColor(ContextCompat.getColor(mContext, R.color.colorRed));
        }

        holder.askValueView.setText(String.format(Locale.ENGLISH, "%07.4f", currency.getAsk()));
        holder.bidValueView.setText(String.format(Locale.ENGLISH, "%07.4f", currency.getBid()));
    }

    @Override
    public int getItemCount() {
        return mCurrencyList.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.currency_name)
        TextView currencyName;

        @BindView(R.id.ask_status)
        ImageView askStatusImage;

        @BindView(R.id.bid_status)
        ImageView bidStatusImage;

        @BindView(R.id.ask_value)
        TextView askValueView;

        @BindView(R.id.bid_value)
        TextView bidValueView;

        DetailsViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
