package com.xuyonghua.paging.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.xuyonghua.paging.entity.Repo;

public class RepoFactory extends DataSource.Factory<Integer, Repo> {
    private MutableLiveData<RepoDataSource> mSourceLiveData =
            new MutableLiveData<>();

    @Override
    public DataSource<Integer, Repo> create() {
        RepoDataSource dataSource = new RepoDataSource();
        mSourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
