package com.jccsisc.myroomdb.iu.crudprofessor.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.jccsisc.myroomdb.db.dao.ProfessorDao;
import com.jccsisc.myroomdb.db.db.AppDB;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public class ProfessorRepositoryImpl implements ProfessorRepository {

    private final ProfessorDao professorDao;
    private final  ExecutorService dbWriteExecutor = Executors.newSingleThreadExecutor();

    public ProfessorRepositoryImpl(Context context) {
        AppDB db = AppDB.getInstance(context);
        professorDao = db.professorDao();
    }

    @Override
    public void isertProfessor(ProfessorEntity professorEntity) {
        dbWriteExecutor.submit(() -> professorDao.insertProfessor(professorEntity));
    }

    @Override
    public LiveData<List<ProfessorEntity>> getAllProfessors() {
        return professorDao.findAllProfessorLiveData();
    }

    public void close() {
        dbWriteExecutor.shutdown();
    }
}
