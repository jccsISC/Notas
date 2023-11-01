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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.jccsisc.myroomdb.databinding.ActivityProfessorBinding binding = ActivityProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Inyección de dependencias para ProfessorViewModel
        ((MyApp) getApplication()).getAppComponent().inject(this);
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
//            saveProfessor();
            return true;
        } else if (id == R.id.nav_delete) {

            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}