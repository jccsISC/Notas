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
    @Insert
    Completable insertProfessor(ProfessorEntity professor);

    @Query("SELECT * FROM tbl_professor")
    Flowable<List<ProfessorEntity>> findAllProfessorFlowable();

    @Query("SELECT * FROM tbl_professor WHERE name LIKE :name")
    ProfessorEntity findProfessorByName(String name);

    @Query("SELECT * FROM tbl_professor WHERE id = :id")
    ProfessorEntity findProfessorById(int id);

    @Update
    Completable updateProfessorById(ProfessorEntity professor);

    @Query("DELETE FROM tbl_professor")
    Completable deleteAllProfessor();

    @Delete
    Completable deleteProfessorById(ProfessorEntity professor);
}
