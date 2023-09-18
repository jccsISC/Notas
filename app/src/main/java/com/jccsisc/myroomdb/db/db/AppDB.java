package com.jccsisc.myroomdb.db.db;

import static com.jccsisc.myroomdb.constans.Constans.DB_PROFESSOR;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jccsisc.myroomdb.db.dao.ProfessorDao;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.db.db
 * Created by Julio Cesar Camacho Silva on 07/08/23
 */
@Database(entities = {ProfessorEntity.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public abstract ProfessorDao professorDao();
}
