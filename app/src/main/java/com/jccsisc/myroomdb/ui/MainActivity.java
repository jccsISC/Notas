package com.jccsisc.myroomdb.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.jccsisc.myroomdb.MyApp;
import com.jccsisc.myroomdb.R;
import com.jccsisc.myroomdb.databinding.ActivityMainBinding;
import com.jccsisc.myroomdb.utils.GlobalFunctions;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    GlobalFunctions globalFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inyección de dependencias para MainViewModel
        ((MyApp) getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
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
            globalFunctions.showToast("Borrar todo aún no funciona.");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}