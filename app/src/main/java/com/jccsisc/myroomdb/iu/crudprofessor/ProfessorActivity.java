package com.jccsisc.myroomdb.iu.crudprofessor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.jccsisc.myroomdb.databinding.ActivityProfessorBinding;
import com.jccsisc.myroomdb.db.db.AppDB;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.iu.crudprofessor.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfessorActivity extends AppCompatActivity {

    private ActivityProfessorBinding binding;
    public static String PROFESSORS_LIST = "professorsList";
    private ProfessorViewModel professorViewModel;
    private AppDB db;
    private ProfessorEntity professor = new ProfessorEntity();
    private final List<ProfessorModel> listProfessors = new ArrayList<>();
//    private DBbyTask dBbyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        professorViewModel = new ViewModelProvider(this).get(ProfessorViewModel.class);

//        db = AppDB.getAppDb(getApplicationContext());
//        dBbyTask = new DBbyTask();

        listeners();
        observers();
    }

    private void listeners() {
        binding.btnSave.setOnClickListener(v -> {

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

            professor.setName(name);
            professor.setEmail(email);

            professorViewModel.insertProfessor(professor);

//            dBbyTask.insertProfessor(professor);
        });
        binding.btnReadProfessors.setOnClickListener(v -> {
            Log.d("READ", "Obteniendo la lista de profesires");
            Intent resultIntent = new Intent();
            resultIntent.putParcelableArrayListExtra(PROFESSORS_LIST, new ArrayList<>(listProfessors));
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        binding.btnFindProfessorsByName.setOnClickListener(v -> {

        });
        binding.btnFinProfessorsById.setOnClickListener(v -> {

        });
        binding.btnUpdateProfessorsById.setOnClickListener(v -> {

        });
        binding.btnDeleteAll.setOnClickListener(v -> {

        });
        binding.btnDeleteProfessorById.setOnClickListener(v -> {

        });
    }

    private void observers() {
//        dBbyTask.readProfessors().observe(this, professors -> {
//            if (professors != null) {
//                for (ProfessorEntity prof : professors) {
//                    Log.i("PROFESSORS", prof.getName());
//                    listProfessors.add(transformprofessorEntityToModel(prof));
//                }
//            }
//        });

        professorViewModel.getAllProfessors().observe(this, professorEntities -> {
            if (professorEntities != null) {
                binding.edtName.setText("");
                binding.edtEmail.setText("");
                for (ProfessorEntity prof : professorEntities) {
                    Log.i("PROFESSORS", prof.getName());
                    listProfessors.add(transformprofessorEntityToModel(prof));
                }
            }
        });
    }

    private ProfessorModel transformprofessorEntityToModel(ProfessorEntity prof) {
        return new ProfessorModel(
                prof.getId(),
                prof.getName(),
                prof.getEmail()
        );
    }

    private class DBbyTask {
        private final ExecutorService executor = Executors.newSingleThreadExecutor();

        public void insertProfessor(final ProfessorEntity professor) {
            executor.submit(() -> {
                db.professorDao().insertProfessor(professor);
                showToast("Guardado correctamente.");
                clearInputFields();
            });
        }

        public LiveData<List<ProfessorEntity>> readProfessors() {
            return db.professorDao().findAllProfessorLiveData();
        }

        private void showToast(String message) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                Toast.makeText(ProfessorActivity.this, message, Toast.LENGTH_SHORT).show();
            });
        }

        public void shutdown() {
            executor.shutdown();
        }

        private void clearInputFields() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                binding.edtName.setText("");
                binding.edtEmail.setText("");
            });
        }
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (dBbyTask != null) {
//            dBbyTask.shutdown();
//        }
//    }
}