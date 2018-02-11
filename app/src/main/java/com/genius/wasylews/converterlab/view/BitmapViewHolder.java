package com.genius.wasylews.converterlab.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.domain.model.Organization;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BitmapViewHolder {
    @BindView(R.id.share_organization_name)
    TextView mOrganizationName;

    @BindView(R.id.share_organization_region)
    TextView mOrganizationRegion;

    @BindView(R.id.share_organization_city)
    TextView mOrganizationCity;

    @BindView(R.id.share_currency_list)
    RecyclerView mRecyclerView;

    private Context mContext;

    @Inject
    public BitmapViewHolder(Context context) {
        mContext = context;
    }

    public Bitmap render(Organization organization) {
        LinearLayout layout = new LinearLayout(mContext);


        LayoutInflater.from(mContext).inflate(R.layout.share_fragment_image_layout,
                layout, true);

        bindView(layout, organization);

        return getViewBitmap(layout);
    }

    private void bindView(View view, Organization organization) {
        ButterKnife.bind(this, view);

        mOrganizationName.setText(organization.getTitle());
        mOrganizationRegion.setText(organization.getRegion());
        mOrganizationCity.setText(organization.getCity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        ShareCurrenciesAdapter adapter = new ShareCurrenciesAdapter(mContext,
                organization.getCurrencies());
        mRecyclerView.setAdapter(adapter);
    }

    private Bitmap getViewBitmap(View view) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(widthSpec, heightSpec);

        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();

        view.layout(0, 0, width, height);

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);

        return bitmap;
    }
}