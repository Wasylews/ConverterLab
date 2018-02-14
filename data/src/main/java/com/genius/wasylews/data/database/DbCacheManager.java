package com.genius.wasylews.data.database;


import com.genius.wasylews.data.database.model.OrganizationCurrencyModel;
import com.genius.wasylews.data.database.model.OrganizationModel;
import com.genius.wasylews.data.database.model.OrganizationModel_Table;
import com.genius.wasylews.data.database.model.mapper.OrganizationCurrencyModelMapper;
import com.genius.wasylews.data.database.model.mapper.OrganizationMapper;
import com.genius.wasylews.data.net.model.ServiceResponse;
import com.genius.wasylews.domain.model.Organization;
import com.raizlabs.android.dbflow.rx2.language.RXSQLite;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class DbCacheManager {

    @Inject
    public DbCacheManager() {
    }

    public Single<List<Organization>> getOrganizationList() {
        return RXSQLite.rx(SQLite.select().from(OrganizationModel.class))
                .queryList()
                .toFlowable()
                .flatMapIterable(organizationModels -> organizationModels)
                .map(OrganizationMapper::transform)
                .toList();
    }

    public Single<Organization> getOrganization(String id) {
        return RXSQLite.rx(SQLite.select().from(OrganizationModel.class)
                .where(OrganizationModel_Table.id.eq(id)))
                .querySingle()
                .toSingle()
                .map(OrganizationMapper::transform);
    }

    public Completable save(ServiceResponse response) {
        return Single.just(response)
                .map(OrganizationCurrencyModelMapper::transform)
                .toFlowable()
                .flatMapIterable(organizationCurrencyModels -> organizationCurrencyModels)
                .doOnNext(OrganizationCurrencyModel::save)
                .toList()
                .toCompletable();
    }
}
