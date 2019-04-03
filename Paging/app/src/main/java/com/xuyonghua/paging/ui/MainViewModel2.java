package com.xuyonghua.paging.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.xuyonghua.paging.entity.Repo;
import com.xuyonghua.paging.repository.RepoDataSource2;
import com.xuyonghua.paging.repository.RepoFactory2;

public class MainViewModel2 extends ViewModel {
    public static final String TAG = MainActivity.class.getSimpleName();
    private LiveData<PagedList<Repo>> repoList;
    private RepoDataSource2 dataSource;
    public static final int PAGE_SIZE = 10;

    public MainViewModel2() {
        RepoFactory2 factory = new RepoFactory2();
        dataSource = factory.create();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(PAGE_SIZE)              //初始化加载的数量
                .build();
        repoList = new LivePagedListBuilder<>(factory, config).build();
    }

    public LiveData<PagedList<Repo>> getConvertList() {
        return repoList;
    }

    public void refreshDataSource() {
        dataSource.invalidate();
    }

    public MutableLiveData<String> getState() {
        return dataSource.getState();
    }
}
