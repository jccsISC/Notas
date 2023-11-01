package com.jccsisc.myroomdb.ui.home;

import static android.app.Activity.RESULT_OK;
import static com.jccsisc.myroomdb.ui.crudprofessor.ProfessorFragment.PROFESSORS_LIST;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.jccsisc.myroomdb.MyApp;
import com.jccsisc.myroomdb.R;
import com.jccsisc.myroomdb.databinding.FragmentHomeBinding;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.crudprofessor.ProfessorActivity;
import com.jccsisc.myroomdb.ui.crudprofessor.model.ProfessorModel;
import com.jccsisc.myroomdb.ui.home.adapter.HomeAdapter;
import com.jccsisc.myroomdb.utils.GlobalFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;
    @Inject
    HomeViewModel mainViewModel;
    @Inject
    GlobalFunctions globalFunctions;
    private ActivityResultLauncher<Intent> professorLauncher;
    private List<ProfessorModel> professorModelList = new ArrayList<>();
    HomeAdapter adapter;

    public HomeFragment() {
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
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new HomeAdapter();

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
//            Intent intent = new Intent(requireContext(), ProfessorActivity.class);
//            professorLauncher.launch(intent);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_professorFragment);
        });
    }

    private void observers() {
        mainViewModel.getAllProfessors().observe(getViewLifecycleOwner(), professorEntities -> {
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

        mainViewModel.getProfessor().observe(getViewLifecycleOwner(),  professorEntities-> {
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
        adapter.setOnItemClickListener(professorModel -> globalFunctions.showToast(professorModel.getName()));
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
}