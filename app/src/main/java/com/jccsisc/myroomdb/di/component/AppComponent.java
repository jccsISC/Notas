package com.jccsisc.myroomdb.di.component;

import com.jccsisc.myroomdb.di.module.AppModule;
import com.jccsisc.myroomdb.ui.crudprofessor.ProfessorActivity;
import com.jccsisc.myroomdb.ui.MainActivity;
import com.jccsisc.myroomdb.ui.crudprofessor.ProfessorFragment;
import com.jccsisc.myroomdb.ui.home.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.di.modules
 * Created by Julio Cesar Camacho Silva on 16/09/23
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(ProfessorActivity professorActivity);
    void inject(HomeFragment homeFragment);
    void inject(ProfessorFragment professorFragment);
}
