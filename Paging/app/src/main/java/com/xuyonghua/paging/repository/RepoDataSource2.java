package com.xuyonghua.paging.repository;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xuyonghua.paging.api.ApiService;
import com.xuyonghua.paging.api.AppRetrofit;
import com.xuyonghua.paging.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xuyonghua.paging.util.Constants.FAIL;
import static com.xuyonghua.paging.util.Constants.SUCCESS;

public class RepoDataSource2 extends PageKeyedDataSource<Integer, Repo> {
    public static final String TAG = RepoDataSource2.class.getSimpleName();
    ApiService apiService;
    int page = 1;
    private MutableLiveData<String> state =
            new MutableLiveData<>();

    public RepoDataSource2() {
        apiService = AppRetrofit.getInstance().create(ApiService.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Repo> callback) {
        Log.d(TAG, "loadInitial:" + params.requestedLoadSize);
        apiService.listRepos("google", 1, params.requestedLoadSize).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Log.d(TAG, "onResponse:" + response.message());
                if (response.code() == 200) {
                    callback.onResult(response.body(), 0, 1);
//                    state.postValue(SUCCESS);
                    state.setValue(SUCCESS);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "onFailure:" + t.getMessage());
                state.postValue(FAIL);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {
        Log.d(TAG, "loadBefore:" + params.requestedLoadSize);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Repo> callback) {
        page++;
        Log.d(TAG, "loadAfter:" + (params.key + 1));
        Log.d(TAG, "loadAfter:" + page);
        apiService.listRepos("xuyonghua", (params.key + 1), 10).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                state.postValue(SUCCESS);
                if (response.body().size() == 0) {
                    Log.d(TAG, "loadAfter:none");
                    return;
                }
                Log.d(TAG, "loadAfter:" + response.body().get(response.body().size() - 1).getFullName());
                callback.onResult(response.body(), (params.key + 1));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                state.postValue(FAIL);
            }
        });
    }

    public MutableLiveData<String> getState() {
        return state;
    }



}
