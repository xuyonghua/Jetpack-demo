package com.xuyonghua.paging.api;

import com.xuyonghua.paging.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user, @Query("page") int page, @Query("per_page") int per_page);
}
