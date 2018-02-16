package com.genius.wasylews.device.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.genius.wasylews.domain.bitmap.BitmapProvider;

import javax.inject.Inject;

public class BitmapProviderUtil implements BitmapProvider {
    private final Context mContext;

    @Inject
    public BitmapProviderUtil(Context context) {
        mContext = context;
    }

    @Override
    public Uri saveBitmap(String name, Bitmap bitmap) {
        String url = MediaStore.Images.Media.insertImage(
                mContext.getContentResolver(), bitmap, name, "");

        return url != null ? Uri.parse(url) : null;
    }
}
