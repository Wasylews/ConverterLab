package com.genius.wasylews.domain.usecase;

import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Flowable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetOrganizationListTest {

    @Mock private Repository mMockRepository;
    private GetOrganizationList mGetOrganizationList;

    @Before
    public void setUp() {
        when(mMockRepository.getOrganizations())
                .thenReturn(Flowable.fromArray(
                        new Organization(10)
                ));

        mGetOrganizationList = new GetOrganizationList(mMockRepository);
    }

    @Test
    public void testGetOrganizationListSuccess() throws Exception {
        Assert.assertEquals(mGetOrganizationList.execute().size(), 1);
        verify(mMockRepository).getOrganizations();
        verifyNoMoreInteractions(mMockRepository);
    }
}