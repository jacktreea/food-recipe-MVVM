package com.example.recipe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class AppExecutors {
    private static AppExecutors instance;
    public static AppExecutors getInstance(){
        if (instance == null){
            instance = new AppExecutors();
        }
            return instance;

    }
    private final ScheduledExecutorService mNetworkIo = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService getmNetworkIo(){
        return mNetworkIo;
    }
}
