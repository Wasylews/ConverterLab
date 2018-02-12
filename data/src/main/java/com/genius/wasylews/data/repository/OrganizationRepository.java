package com.genius.wasylews.data.repository;

import android.util.Log;

import com.genius.wasylews.data.database.DbCacheManager;
import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.device.preferences.PreferencesManager;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.network.NetworkManager;
import com.genius.wasylews.domain.repository.Repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;


public class OrganizationRepository implements Repository {

    private final RestService mRestAdapter;

    private final DbCacheManager mCacheManager;

    private final PreferencesManager mPreferencesManager;

    private final NetworkManager mConnectionManager;

    private static final String LAST_DATE_KEY = "LAST_UPDATE_DATE_KEY";

    private static final String TAG = OrganizationRepository.class.getSimpleName();

    @Inject
    public OrganizationRepository(RestService adapter,
                                  DbCacheManager cacheManager,
                                  PreferencesManager preferencesManager,
                                  NetworkManager connectionManager) {
        mRestAdapter = adapter;
        mCacheManager = cacheManager;
        mPreferencesManager = preferencesManager;
        mConnectionManager = connectionManager;
    }

    @Override
    public Single<List<Organization>> getOrganizations() {
        return fetchOrganizations()
                .onErrorComplete()
                .andThen(mCacheManager.getOrganizationList());
    }

    public Completable fetchOrganizations() {
        if (!mConnectionManager.isConnected()) {
            return Completable.error(new Exception("No internet"));
        }

        Log.d(TAG, "Fetching from service");
        return mRestAdapter.getOrganizations()
                .doOnSuccess(response -> {
                    Date lastDate = mPreferencesManager.getDate(LAST_DATE_KEY);
                    Date currentDate = response.getDate();

                    if (currentDate != null && currentDate.after(lastDate)) {
                        Log.d(TAG, "Update date");
                        mPreferencesManager.putDate(LAST_DATE_KEY, currentDate);

                        mCacheManager.save(response);
                    }
                }).toCompletable();
    }

    @Override
    public Single<Organization> getOrganization(String id) {
        return mCacheManager.getOrganization(id);
    }
}
