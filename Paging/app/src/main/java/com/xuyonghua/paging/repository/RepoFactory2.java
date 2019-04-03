package com.xuyonghua.paging.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.xuyonghua.paging.entity.Repo;

public class RepoFactory2 extends DataSource.Factory<Integer, Repo> {
    private MutableLiveData<RepoDataSource2> mSourceLiveData =
            new MutableLiveData<>();

    @Override
    public RepoDataSource2 create() {
        RepoDataSource2 dataSource = new RepoDataSource2();
        mSourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
