package com.jccsisc.myroomdb.ui.main;

import static com.jccsisc.myroomdb.ui.crudprofessor.ProfessorActivity.PROFESSORS_LIST;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.jccsisc.myroomdb.R;
import com.jccsisc.myroomdb.databinding.ActivityMainBinding;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.crudprofessor.ProfessorActivity;
import com.jccsisc.myroomdb.ui.crudprofessor.model.ProfessorModel;
import com.jccsisc.myroomdb.ui.main.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new MainAdapter();

        professorLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();

                if (data != null) {
                    professorModelList = data.getParcelableArrayListExtra(PROFESSORS_LIST);

                    if (professorModelList != null && !professorModelList.isEmpty()) {
                        initRvProfessor();
                    }
                }
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
            professorModelList.clear();
            if (professorEntities != null) {

                if (professorEntities.size() > 0) {
                    for (ProfessorEntity professorEntity : professorEntities) {
                        professorModelList.add(transformprofessorEntityToModel(professorEntity));
                    }

                    initRvProfessor();
                }
            }
        });

        mainViewModel.getProfessor().observe(this,  professorEntities-> {
            professorModelList.clear();
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
        String query ="%" + newText.toUpperCase(Locale.ROOT) + "%";
        mainViewModel.searchProfessorByName(query);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_delete_all) {
            Toast.makeText(this, "Borrar todo", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}