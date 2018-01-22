package com.genius.wasylews.data.repository;

import android.util.Log;

import com.genius.wasylews.data.database.model.OrganizationCurrencyModel;
import com.genius.wasylews.data.database.model.OrganizationModel;
import com.genius.wasylews.data.database.model.OrganizationModel_Table;
import com.genius.wasylews.data.database.model.mapper.OrganizationCurrencyModelMapper;
import com.genius.wasylews.data.database.model.mapper.OrganizationMapper;
import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.device.preferences.PreferencesManager;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.network.NetworkManager;
import com.genius.wasylews.domain.repository.Repository;
import com.raizlabs.android.dbflow.rx2.language.RXSQLite;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class OrganizationRepository implements Repository {

    private final RestService mRestAdapter;

    private final PreferencesManager mPreferencesManager;

    private final NetworkManager mConnectionManager;

    private static final String LAST_DATE_KEY = "LAST_UPDATE_DATE_KEY";

    private static final String TAG = OrganizationRepository.class.getSimpleName();

    @Inject
    public OrganizationRepository(RestService adapter,
                                  PreferencesManager preferencesManager,
                                  NetworkManager connectionManager) {
        mRestAdapter = adapter;
        mPreferencesManager = preferencesManager;
        mConnectionManager = connectionManager;
    }

    @Override
    public Single<List<Organization>> getOrganizations() {
        return RXSQLite.rx(SQLite.select().from(OrganizationModel.class))
                .queryList()
                .doOnSubscribe((disposable) -> this.fetchOrganizations())
                .toFlowable()
                .flatMapIterable(organizationModels -> organizationModels)
                .map(OrganizationMapper::transform)
                .toList();
    }

    public void fetchOrganizations() {
        if (!mConnectionManager.isConnected()) {
            return;
        }

        Log.d(TAG, "Fetching from service");
        mRestAdapter.getOrganizations()
                .filter(serviceResponse -> {
                    Date lastDate = mPreferencesManager.getDate(LAST_DATE_KEY);
                    Date currentDate = serviceResponse.getDate();

                    if (currentDate != null) {
                        if (currentDate.after(lastDate)) {
                            Log.d(TAG, "Update date");
                            mPreferencesManager.putDate(LAST_DATE_KEY, currentDate);
                            return true;
                        }
                    }
                    return false;
                })
                .map(OrganizationCurrencyModelMapper::transform)
                .toFlowable()
                .flatMapIterable(organizationCurrencyModels -> organizationCurrencyModels)
                .observeOn(Schedulers.io())
                .subscribe(OrganizationCurrencyModel::save);
    }

    @Override
    public Single<Organization> getOrganization(String id) {
        return RXSQLite.rx(SQLite.select().from(OrganizationModel.class)
                .where(OrganizationModel_Table.id.eq(id)))
                .querySingle()
                .toSingle()
                .map(OrganizationMapper::transform);
    }
}
