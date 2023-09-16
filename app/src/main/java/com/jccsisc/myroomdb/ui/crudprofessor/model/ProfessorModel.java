package com.jccsisc.myroomdb.ui.crudprofessor.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor.model
 * Created by Julio Cesar Camacho Silva on 14/08/23
 */
public class ProfessorModel implements Parcelable {
    private int id;
    private String name;
    private String email;

    public ProfessorModel() {
    }

    public ProfessorModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    protected ProfessorModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<ProfessorModel> CREATOR = new Creator<ProfessorModel>() {
        @Override
        public ProfessorModel createFromParcel(Parcel in) {
            return new ProfessorModel(in);
        }

        @Override
        public ProfessorModel[] newArray(int size) {
            return new ProfessorModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorModel that = (ProfessorModel) o;
        return id == that.id && name.equals(that.name) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
