package com.genius.wasylews.converterlab.view;


import com.genius.wasylews.domain.model.Organization;

import java.util.List;

public interface BaseHomeView {

    void showOrganizations(List<Organization> list);

    void showOrganizationDetails(Organization organization);

    void showProgress();

    void hideProgress();
}
