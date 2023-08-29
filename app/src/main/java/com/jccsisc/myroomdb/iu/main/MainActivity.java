package com.jccsisc.myroomdb.iu.main;

import static com.jccsisc.myroomdb.iu.crudprofessor.ProfessorActivity.PROFESSORS_LIST;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.jccsisc.myroomdb.databinding.ActivityMainBinding;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.iu.crudprofessor.ProfessorActivity;
import com.jccsisc.myroomdb.iu.crudprofessor.model.ProfessorModel;
import com.jccsisc.myroomdb.iu.main.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private ActivityResultLauncher<Intent> professorLauncher;
    private List<ProfessorModel> professorModelList = new ArrayList<>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new MainAdapter();

        professorLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();

//                if (data != null) {
//                    professorModelList = data.getParcelableArrayListExtra(PROFESSORS_LIST);
//
//                    if (professorModelList != null && !professorModelList.isEmpty()) {
//                        initRvProfessor();
//                    }
//                }
            }
        });

        listeners();
        observers();
    }

    private void listeners() {
        binding.searchProfessor.setOnQueryTextListener(this);
        binding.btnFab.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfessorActivity.class);
            professorLauncher.launch(intent);
        });
    }

    private void observers() {
        mainViewModel.getAllProfessors().observe(this, professorEntities -> {
            if (professorEntities != null) {

                if (professorEntities.size() > 0) {
                    for (ProfessorEntity professorEntity : professorEntities) {
                        professorModelList.add(transformprofessorEntityToModel(professorEntity));
                    }

                    initRvProfessor();
                }
            }
        });
    }

    private void initRvProfessor() {
        binding.rvProfessors.setAdapter(adapter);
        adapter.submitList(professorModelList);
    }

    private ProfessorModel transformprofessorEntityToModel(ProfessorEntity prof) {
        return new ProfessorModel(
                prof.getId(),
                prof.getName(),
                prof.getEmail()
        );
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}