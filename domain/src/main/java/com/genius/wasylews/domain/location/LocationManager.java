package com.genius.wasylews.domain.location;

import com.genius.wasylews.domain.model.Location;

import io.reactivex.Flowable;

public interface LocationManager {

    Flowable<Location> findLocation(String name);
}
