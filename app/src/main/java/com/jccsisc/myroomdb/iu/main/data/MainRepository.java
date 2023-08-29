package com.jccsisc.myroomdb.iu.main.data;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.main.data
 * Created by Julio Cesar Camacho Silva on 28/08/23
 */
public interface MainRepository {
    Flowable<List<ProfessorEntity>> getAllProfessors();
}
