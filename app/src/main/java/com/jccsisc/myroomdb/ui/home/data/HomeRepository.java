package com.jccsisc.myroomdb.ui.home.data;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.main.data
 * Created by Julio Cesar Camacho Silva on 28/08/23
 */
public interface HomeRepository {
    Flowable<List<ProfessorEntity>> getAllProfessors();
    Flowable<List<ProfessorEntity>> getProfessorsByName(String name);
}
