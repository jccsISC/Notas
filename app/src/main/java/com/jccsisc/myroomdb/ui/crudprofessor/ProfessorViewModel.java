package com.jccsisc.myroomdb.ui.crudprofessor;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.crudprofessor.data.ProfessorRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public class ProfessorViewModel extends ViewModel {

    private final ProfessorRepositoryImpl repository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public ProfessorViewModel(ProfessorRepositoryImpl professorRepository) {
        repository = professorRepository;
    }

    public void insertProfessor(ProfessorEntity professorEntity) {
        Disposable disposable = repository.isertProfessor(professorEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    //Success
                    Log.d("ok", "success");

                }, throwable -> {
                   //handle error
                    Log.e("error", "error");
                });

        disposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
