package com.genius.wasylews.converterlab.view;


import com.genius.wasylews.domain.model.Organization;

public interface BaseDetailsView {

    void showOrganization(Organization organization);

    void openLink(String link);

    void openMap(Organization organization);

    void makeCall(String phone);
}
