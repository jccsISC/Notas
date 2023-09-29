package com.jccsisc.myroomdb.utils;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.utils
 * Created by Julio Cesar Camacho Silva on 16/09/23
 */
public class GlobalFunctions {

    private final Context context;

    @Inject
    public GlobalFunctions(Context context) {
        this.context = context;
    }
    public void showToast(String messaage) {
        Toast.makeText(context, messaage, Toast.LENGTH_SHORT).show();
    }

}
