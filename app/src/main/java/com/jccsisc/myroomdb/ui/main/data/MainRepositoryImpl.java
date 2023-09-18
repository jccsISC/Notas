package com.jccsisc.myroomdb.ui.main.data;

import com.jccsisc.myroomdb.db.dao.ProfessorDao;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.main.data
 * Created by Julio Cesar Camacho Silva on 28/08/23
 */
public class MainRepositoryImpl implements MainRepository {

    private final ProfessorDao professorDao;

    @Inject
    public MainRepositoryImpl(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @Override
    public Flowable<List<ProfessorEntity>> getAllProfessors() {
        return professorDao.findAllProfessorFlowable();
    }

    @Override
    public Flowable<List<ProfessorEntity>> getProfessorsByName(String name) {
        return professorDao.findProfessorsByName(name);
    }
}
