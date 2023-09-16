package com.jccsisc.myroomdb.ui.crudprofessor.data;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import io.reactivex.rxjava3.core.Completable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor.data
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public interface ProfessorRepository {
    Completable isertProfessor(ProfessorEntity professorEntity);

//    Flowable<List<ProfessorEntity>> getAllProfessors();
}
