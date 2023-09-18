package com.jccsisc.myroomdb;

import android.app.Application;

import com.jccsisc.myroomdb.di.component.AppComponent;
import com.jccsisc.myroomdb.di.component.DaggerAppComponent;
import com.jccsisc.myroomdb.di.module.AppModule;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb
 * Created by Julio Cesar Camacho Silva on 16/09/23
 */
public class MyApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
