package com.xyh.jetpacktest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String userId;
    private LiveData<User> user;

    public void init(String userId) {
        this.userId = userId;
    }

    public LiveData<User> getUser() {
        return user;
    }
}
