package com.jccsisc.myroomdb.iu.crudprofessor.data;

import androidx.lifecycle.LiveData;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.iu.crudprofessor.model.ProfessorModel;

import java.util.List;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor.data
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public interface ProfessorRepository {
    void isertProfessor(ProfessorEntity professorEntity);

    LiveData<List<ProfessorEntity>> getAllProfessors();
}
