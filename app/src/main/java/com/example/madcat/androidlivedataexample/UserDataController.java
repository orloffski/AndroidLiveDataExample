package com.example.madcat.androidlivedataexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public class UserDataController {

    private static UserDataController instance;
    private MutableLiveData<User> liveData = new MutableLiveData<>();

    private UserDataController(){}

    public static UserDataController getInstance() {
        if(instance == null){
            instance = new UserDataController();
        }
        return instance;
    }

    public LiveData<User> getData(){
        return liveData;
    }

    public void addData(String value){
        liveData.setValue(User.getUser(value));
    }
}