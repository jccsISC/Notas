package com.jccsisc.myroomdb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;

import java.util.List;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.db.dao
 * Created by Julio Cesar Camacho Silva on 07/08/23
 */
@Dao
public interface ProfessorDao {
    @Insert
    void insertProfessor(ProfessorEntity professor);

    @Query("SELECT * FROM tbl_professor")
    LiveData<List<ProfessorEntity>> findAllProfessorLiveData();




    @Query("SELECT * FROM tbl_professor WHERE name LIKE :name")
    ProfessorEntity findProfessorByName(String name);

    @Query("SELECT * FROM tbl_professor WHERE id = :id")
    ProfessorEntity findProfessorById(int id);

    @Update
    void updateProfessorById(ProfessorEntity professor);

    @Query("DELETE FROM tbl_professor")
    void deleteAllProfessor();

    @Delete
    void deleteProfessorById(ProfessorEntity professor);
}
