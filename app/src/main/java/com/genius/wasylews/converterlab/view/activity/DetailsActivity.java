package com.genius.wasylews.converterlab.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.domain.model.Organization;

import dagger.android.AndroidInjection;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_ORGANIZATION_ID = "ORGANIZATION_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public static Intent newIntent(Context context, Organization organization) {
        Intent mapIntent = new Intent(context, DetailsActivity.class);
        mapIntent.putExtra(EXTRA_ORGANIZATION_ID, organization.getId());
        return mapIntent;
    }
}
