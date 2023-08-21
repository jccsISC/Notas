package com.jccsisc.myroomdb.db.entity;

import static com.jccsisc.myroomdb.constans.Constans.TABLE_NAME_PROFESSOR;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.db.entity
 * Created by Julio Cesar Camacho Silva on 07/08/23
 */
@Entity(tableName = TABLE_NAME_PROFESSOR)
public class ProfessorEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
