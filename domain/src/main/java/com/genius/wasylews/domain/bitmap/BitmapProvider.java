package com.genius.wasylews.domain.bitmap;

import android.graphics.Bitmap;
import android.net.Uri;

public interface BitmapProvider {

    Uri saveBitmap(String name, Bitmap bitmap);
}
