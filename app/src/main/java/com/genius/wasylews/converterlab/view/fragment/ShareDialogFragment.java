package com.genius.wasylews.converterlab.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.converterlab.presenter.SharePresenter;
import com.genius.wasylews.converterlab.view.BitmapViewHolder;
import com.genius.wasylews.domain.bitmap.BitmapProvider;
import com.genius.wasylews.domain.model.Organization;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatDialogFragment;


public class ShareDialogFragment extends DaggerAppCompatDialogFragment implements BaseShareView {

    private static final String ORGANIZATION_KEY = "ORGANIZATION_ID_KEY";
    public static String TAG = ShareDialogFragment.class.getSimpleName();

    @Inject
    SharePresenter mPresenter;

    @Inject
    BitmapViewHolder mBitmapViewHolder;

    @Inject
    BitmapProvider mBitmapProvider;

    @BindView(R.id.share_dialog_button)
    Button mShareButton;

    @BindView(R.id.share_dialog_image)
    ImageView mContentView;
    private Organization mOrganization;
    private Bitmap mBitmap;

    public static ShareDialogFragment newInstance(Organization organization) {
        ShareDialogFragment dialogFragment = new ShareDialogFragment();

        Bundle args = new Bundle();
        args.putString(ORGANIZATION_KEY, organization.getId());

        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.share_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);

        mShareButton.setOnClickListener(view -> mPresenter.shareOrganization());

        mContentView.setDrawingCacheEnabled(true);

        String organizationId = getArguments().getString(ORGANIZATION_KEY);
        mPresenter.setView(this);
        mPresenter.loadOrganization(organizationId);

        return rootView;
    }

    @Override
    public void showOrganization(Organization organization) {
        mBitmap = mBitmapViewHolder.render(organization);
        mOrganization = organization;
        mContentView.setImageBitmap(mBitmap);
    }

    @Override
    public void share() {
        Uri uri = mBitmapProvider.saveBitmap(mOrganization.getTitle(), mBitmap);

        if (uri != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
            shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntent,
                    getString(R.string.share_title)));
        }
    }

}
