package com.jccsisc.myroomdb.iu.crudprofessor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.iu.crudprofessor.data.ProfessorRepositoryImpl;

import java.util.List;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public class ProfessorViewModel extends AndroidViewModel  {

    private final ProfessorRepositoryImpl repository;
        private final LiveData<List<ProfessorEntity>> allProfessors;
    public ProfessorViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfessorRepositoryImpl(application);
        allProfessors = repository.getAllProfessors();
    }

    public void insertProfessor(ProfessorEntity professorEntity) {
        repository.isertProfessor(professorEntity);
    }

    public LiveData<List<ProfessorEntity>> getAllProfessors() {
        return allProfessors;
    }

    @Override
    protected void onCleared() {
        repository.close();
        super.onCleared();
    }
}
