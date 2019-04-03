package com.xuyonghua.paging.repository;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xuyonghua.paging.api.ApiService;
import com.xuyonghua.paging.api.AppRetrofit;
import com.xuyonghua.paging.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDataSource extends PositionalDataSource<Repo> {
    public static final String TAG = RepoDataSource.class.getSimpleName();
    ApiService apiService;
    int page = 1;

    public RepoDataSource() {
        apiService = AppRetrofit.getInstance().create(ApiService.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Repo> callback) {
        apiService.listRepos("xuyonghua", 1, 10).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d(TAG, "loadInitial:" + response.body().size());
                callback.onResult(response.body(), 0, 2000);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<Repo> callback) {
        Log.d(TAG, "loadRange:" + params.startPosition + "---" + params.loadSize);
        page++;
        apiService.listRepos("xuyonghua", 2, 10).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d(TAG, "loadInitial:" + response.body().size());
                callback.onResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }
}
