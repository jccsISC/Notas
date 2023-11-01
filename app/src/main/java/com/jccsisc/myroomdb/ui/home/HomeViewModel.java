package com.jccsisc.myroomdb.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.home.data.HomeRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu
 * Created by Julio Cesar Camacho Silva on 28/08/23
 */
public class HomeViewModel extends ViewModel {

    private final HomeRepositoryImpl repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<List<ProfessorEntity>> allProfessors = new MutableLiveData<>();
    private final MutableLiveData<List<ProfessorEntity>> getProfessors = new MutableLiveData<>();

    @Inject
    public HomeViewModel(HomeRepositoryImpl mainRepository) {
        this.repository = mainRepository;
        loadProfessors();
    }

    public LiveData<List<ProfessorEntity>> getAllProfessors() {
        return allProfessors;
    }


    public LiveData<List<ProfessorEntity>> getProfessor() {
        return getProfessors;
    }

    private void loadProfessors() {
        Disposable disposable = repository.getAllProfessors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allProfessors::setValue);

        disposables.add(disposable);
    }

    public void searchProfessorByName(String name) {
        Disposable disposable = repository.getProfessorsByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getProfessors::setValue);

        disposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
