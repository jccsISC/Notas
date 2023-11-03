package com.jccsisc.myroomdb.ui.crudprofessor;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jccsisc.myroomdb.MyApp;
import com.jccsisc.myroomdb.databinding.FragmentProfessorBinding;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.crudprofessor.model.ProfessorModel;
import com.jccsisc.myroomdb.utils.GlobalFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ProfessorFragment extends Fragment {

    private FragmentProfessorBinding binding;
    public static String PROFESSORS_LIST = "professorsList";

    @Inject
    ProfessorViewModel professorViewModel;
    @Inject
    GlobalFunctions globalFunctions;
    private final ProfessorEntity professor = new ProfessorEntity();
    private final List<ProfessorModel> listProfessors = new ArrayList<>();

    public ProfessorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApp) context.getApplicationContext()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfessorBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        binding.cardImgProfessor.setOnClickListener(v -> globalFunctions.showToast("Aun no está esta funcionalidad"));
        binding.btnSave.setOnClickListener(v -> saveProfessor());
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

        professorViewModel.insertProfessor.observe(getViewLifecycleOwner(), result -> {
            switch (result.status) {
                case LOADING:
                    Log.d("INSERT", "Cargando...");
                    break;
                case SUCCESS:
                    globalFunctions.showToast("Se guardó correctamente");
                    binding.edtName.setText("");
                    binding.edtEmail.setText("");
                    break;
                case ERROR:
                    globalFunctions.showToast("Ocurrió un error al intentar guardar, intenta de nuevo.");
                    break;
            }
        });
    }

    private void saveProfessor() {
        String name = binding.edtName.getText().toString();
        String email = binding.edtEmail.getText().toString().toLowerCase(Locale.ROOT);


        if (validFields(name, email)) {
            professor.setName(name.toUpperCase(Locale.ROOT));
            professor.setEmail(email.toUpperCase(Locale.ROOT));

            professorViewModel.insertProfessor(professor);
        }
    }

    private boolean validFields(String name, String email) {
        boolean isValid = true;

        if (name.isEmpty()) {
            globalFunctions.showToast("Ingresa el nombre");
            isValid = false;
        } else if (email.isEmpty()) {
            globalFunctions.showToast("Ingresa el correo");
            isValid = false;
        }

        return isValid;
    }

    private ProfessorModel transformprofessorEntityToModel(ProfessorEntity prof) {
        return new ProfessorModel(
                prof.getId(),
                prof.getName(),
                prof.getEmail()
        );
    }
}