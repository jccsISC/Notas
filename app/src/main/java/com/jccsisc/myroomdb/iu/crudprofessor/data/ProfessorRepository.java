package com.jccsisc.myroomdb.iu.crudprofessor.data;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor.data
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public interface ProfessorRepository {
    Completable isertProfessor(ProfessorEntity professorEntity);

//    Flowable<List<ProfessorEntity>> getAllProfessors();
}
