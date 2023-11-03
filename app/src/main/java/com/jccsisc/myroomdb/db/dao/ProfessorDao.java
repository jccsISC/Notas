package com.jccsisc.myroomdb.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.db.dao
 * Created by Julio Cesar Camacho Silva on 07/08/23
 */
@Dao
public interface ProfessorDao {
    /**
     * Completable: No necesitamos una respuesta en concreto solo saber si ya se ejecutó la acción
     * Se usa usalmente para: INSERT, UPDATE, DELETE
     * */
    @Insert
    Completable insertProfessor(ProfessorEntity professor);

    /**
     * Flowable: Necesitamos la respuesta en tiempo real, este se utilizar para los SELECT
     * estar escuchando el cambio y el dato esperado.
     * */
    @Query("SELECT * FROM tbl_professor")
    Flowable<List<ProfessorEntity>> findAllProfessorFlowable();

    @Query("SELECT * FROM tbl_professor WHERE name LIKE :name")
    Flowable<List<ProfessorEntity>> findProfessorsByName(String name);

    @Query("SELECT * FROM tbl_professor WHERE id = :id")
    ProfessorEntity findProfessorById(int id);

    @Update
    Completable updateProfessorById(ProfessorEntity professor);

    @Query("DELETE FROM tbl_professor")
    Completable deleteAllProfessor();

    @Delete
    Completable deleteProfessorById(ProfessorEntity professor);
}
