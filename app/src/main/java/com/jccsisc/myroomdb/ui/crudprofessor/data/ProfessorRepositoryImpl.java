package com.jccsisc.myroomdb.ui.crudprofessor.data;

import android.content.Context;

import com.jccsisc.myroomdb.db.dao.ProfessorDao;
import com.jccsisc.myroomdb.db.db.AppDB;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import io.reactivex.rxjava3.core.Completable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public class ProfessorRepositoryImpl implements ProfessorRepository {

    private final ProfessorDao professorDao;

    public ProfessorRepositoryImpl(Context context) {
        AppDB db = AppDB.getInstance(context);
        professorDao = db.professorDao();
    }

    @Override
    public Completable isertProfessor(ProfessorEntity professorEntity) {
        return professorDao.insertProfessor(professorEntity);
    }

//    @Override
//    public Flowable<List<ProfessorEntity>> getAllProfessors() {
//        return professorDao.findAllProfessorFlowable();
//    }

}
