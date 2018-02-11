package com.genius.wasylews.converterlab.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.domain.model.Currency;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareCurrenciesAdapter extends RecyclerView.Adapter<ShareCurrenciesAdapter.CurrencyViewHolder> {

    private final LayoutInflater mInflater;
    private final List<Currency> mCurrencyList;

    public ShareCurrenciesAdapter(Context context, List<Currency> currencyList) {
        mInflater = LayoutInflater.from(context);
        mCurrencyList = currencyList;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.share_fragment_image_currency_row_layout, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Currency currency = mCurrencyList.get(position);

        holder.currencyCode.setText(currency.getCode());
        holder.currencyAskBid.setText(String.format(Locale.ENGLISH,
                "%05.2f/%05.2f", currency.getAsk(), currency.getBid()));
    }

    @Override
    public int getItemCount() {
        return mCurrencyList.size();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.share_currency_code)
        TextView currencyCode;

        @BindView(R.id.share_currency_ask_bid)
        TextView currencyAskBid;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
