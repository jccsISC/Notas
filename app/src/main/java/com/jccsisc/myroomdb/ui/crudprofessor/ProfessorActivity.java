package com.jccsisc.myroomdb.ui.crudprofessor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jccsisc.myroomdb.MyApp;
import com.jccsisc.myroomdb.R;
import com.jccsisc.myroomdb.databinding.ActivityProfessorBinding;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.crudprofessor.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ProfessorActivity extends AppCompatActivity {

    private ActivityProfessorBinding binding;
    public static String PROFESSORS_LIST = "professorsList";

    @Inject ProfessorViewModel professorViewModel;


    private final ProfessorEntity professor = new ProfessorEntity();
    private final List<ProfessorModel> listProfessors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Inyección de dependencias para ProfessorViewModel
        ((MyApp) getApplication()).getAppComponent().inject(this);

        listeners();
        observers();
    }

    private void listeners() {

//        binding.btnReadProfessors.setOnClickListener(v -> {
//            Log.d("READ", "Obteniendo la lista de profesires");
//            Intent resultIntent = new Intent();
//            resultIntent.putParcelableArrayListExtra(PROFESSORS_LIST, new ArrayList<>(listProfessors));
//            setResult(RESULT_OK, resultIntent);
//            finish();
//        });
        binding.cardImgProfessor.setOnClickListener(v -> {
            Toast.makeText(this, "Aun no está esta funcionalidad", Toast.LENGTH_SHORT).show();
        });
    }

    private void observers() {
//        professorViewModel.getAllProfessors().observe(this, professorEntities -> {
//            if (professorEntities != null) {
//                binding.edtName.setText("");
//                binding.edtEmail.setText("");
//                for (ProfessorEntity prof : professorEntities) {
//                    Log.i("PROFESSORS", prof.getName());
//                    listProfessors.add(transformprofessorEntityToModel(prof));
//                }
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_professor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_save) {
            saveProfessor();
            return true;
        } else if (id == R.id.nav_delete) {

            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveProfessor() {
        String name = binding.edtName.getText().toString();
        String email = binding.edtEmail.getText().toString().toLowerCase(Locale.ROOT);

        if (name.isEmpty()) {
            Toast.makeText(this, "Ingresa el nombre", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Ingresa el correo", Toast.LENGTH_SHORT).show();
            return;
        }

        professor.setName(name.toUpperCase(Locale.ROOT));
        professor.setEmail(email.toUpperCase(Locale.ROOT));

        professorViewModel.insertProfessor(professor);
    }

    private ProfessorModel transformprofessorEntityToModel(ProfessorEntity prof) {
        return new ProfessorModel(
                prof.getId(),
                prof.getName(),
                prof.getEmail()
        );
    }
}