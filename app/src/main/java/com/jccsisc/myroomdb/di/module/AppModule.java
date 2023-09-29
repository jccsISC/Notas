package com.jccsisc.myroomdb.di.module;

import static com.jccsisc.myroomdb.constans.Constans.DB_PROFESSOR;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.jccsisc.myroomdb.db.dao.ProfessorDao;
import com.jccsisc.myroomdb.db.db.AppDB;
import com.jccsisc.myroomdb.ui.crudprofessor.ProfessorViewModel;
import com.jccsisc.myroomdb.ui.crudprofessor.data.ProfessorRepositoryImpl;
import com.jccsisc.myroomdb.ui.main.MainViewModel;
import com.jccsisc.myroomdb.ui.main.data.MainRepositoryImpl;
import com.jccsisc.myroomdb.utils.GlobalFunctions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.di.modules
 * Created by Julio Cesar Camacho Silva on 16/09/23
 */
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    /**
     *  Context
     * */
    @Provides
    public Context provideApplicationContext() {
        return application;
    }

    /**
     * Room Data base
     * */
    @Provides
    @Singleton
    AppDB provideAppDB(Application application) {
        return Room.databaseBuilder(application, AppDB.class, DB_PROFESSOR).build();
    }

    @Provides
    @Singleton
    ProfessorDao provideProfessorDao(AppDB appDB) {
        return appDB.professorDao();
    }

    /**
     * Professor
     */
    @Provides
    @Singleton
    ProfessorRepositoryImpl provideProfessorRepositoryImpl(ProfessorDao professorDao) {
        return new ProfessorRepositoryImpl(professorDao);
    }

    @Provides
    @Singleton
    ProfessorViewModel provideProfessorViewModel(ProfessorRepositoryImpl repository) {
        return new ProfessorViewModel(repository);
    }


    /**
     * Main
     * */
    @Provides
    @Singleton
    MainRepositoryImpl provideMainRepositoryImpl(ProfessorDao professorDao) {
        return new MainRepositoryImpl(professorDao);
    }

    @Provides
    @Singleton
    MainViewModel provideMainViewModel(MainRepositoryImpl repository) {
        return new MainViewModel(repository);
    }

}
