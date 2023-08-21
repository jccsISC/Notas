package com.jccsisc.myroomdb.iu;

import static com.jccsisc.myroomdb.iu.crudprofessor.ProfessorActivity.PROFESSORS_LIST;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.jccsisc.myroomdb.databinding.ActivityMainBinding;
import com.jccsisc.myroomdb.iu.crudprofessor.ProfessorActivity;
import com.jccsisc.myroomdb.iu.crudprofessor.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> professorLauncher;
    private List<ProfessorModel> professorModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.jccsisc.myroomdb.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        professorLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();

                if (data != null) {
                    List<ProfessorModel> listExtras = data.getParcelableArrayListExtra(PROFESSORS_LIST);

                    if (listExtras != null && !listExtras.isEmpty()) {
                        Log.i("lista", listExtras.get(0).getName());
                    }
                }
            }
        });


        binding.btnProfessor.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfessorActivity.class);
            professorLauncher.launch(intent);
        });
    }
}