package com.jccsisc.myroomdb.db.db.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.db.db.migrations
 * Created by Julio Cesar Camacho Silva on 14/08/23
 */
public class Migrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("");
        }
    };

}
