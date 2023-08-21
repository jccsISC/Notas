package com.jccsisc.myroomdb.db.db;

import static com.jccsisc.myroomdb.constans.Constans.DB_PROFESSOR;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jccsisc.myroomdb.db.dao.ProfessorDao;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.db.db
 * Created by Julio Cesar Camacho Silva on 07/08/23
 */
@Database(entities = {ProfessorEntity.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static volatile AppDB INSTANCE_DB;
    public abstract ProfessorDao professorDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDB getAppDb(Context context) {
        if (INSTANCE_DB == null) {
            synchronized (AppDB.class) {
                if (INSTANCE_DB == null) {
                    INSTANCE_DB = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDB.class, DB_PROFESSOR).build();
                }
            }
        }
        return INSTANCE_DB;
    }
}
