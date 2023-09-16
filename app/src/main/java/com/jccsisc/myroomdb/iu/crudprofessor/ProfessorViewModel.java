package com.jccsisc.myroomdb.iu.crudprofessor;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.iu.crudprofessor.data.ProfessorRepositoryImpl;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.crudprofessor
 * Created by Julio Cesar Camacho Silva on 21/08/23
 */
public class ProfessorViewModel extends AndroidViewModel {

    private final ProfessorRepositoryImpl repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
//    private final MutableLiveData<List<ProfessorEntity>> allProfessors = new MutableLiveData<>();

    public ProfessorViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfessorRepositoryImpl(application);
//        loadProfessors();
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

//    public LiveData<List<ProfessorEntity>> getAllProfessors() {
//        return allProfessors;
//    }

//    private void loadProfessors() {
//        Disposable disposable = repository.getAllProfessors()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(allProfessors::setValue);
//
//        disposables.add(disposable);
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
