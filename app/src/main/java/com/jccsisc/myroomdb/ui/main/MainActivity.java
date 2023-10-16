package com.jccsisc.myroomdb.ui.main;

import static com.jccsisc.myroomdb.ui.crudprofessor.ProfessorActivity.PROFESSORS_LIST;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.jccsisc.myroomdb.MyApp;
import com.jccsisc.myroomdb.R;
import com.jccsisc.myroomdb.databinding.ActivityMainBinding;
import com.jccsisc.myroomdb.db.entity.ProfessorEntity;
import com.jccsisc.myroomdb.ui.crudprofessor.ProfessorActivity;
import com.jccsisc.myroomdb.ui.crudprofessor.model.ProfessorModel;
import com.jccsisc.myroomdb.ui.main.adapter.MainAdapter;
import com.jccsisc.myroomdb.utils.GlobalFunctions;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.model.CarouselType;
import org.imaginativeworld.whynotimagecarousel.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator2;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivityMainBinding binding;
    @Inject
    MainViewModel mainViewModel;
    @Inject
    GlobalFunctions globalFunctions;
    private ActivityResultLauncher<Intent> professorLauncher;
    private List<ProfessorModel> professorModelList = new ArrayList<>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inyección de dependencias para MainViewModel
        ((MyApp) getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        binding.carousel.registerLifecycle(getLifecycle());
        binding.carousel.setScaleOnScroll(true);

//        binding.carousel.setCarouselPadding(Utils.dpToPx(16, this));
        binding.carousel.setCarouselPaddingStart(Utils.dpToPx(5, this));
        binding.carousel.setCarouselPaddingTop(Utils.dpToPx(10, this));
        binding.carousel.setCarouselPaddingEnd(Utils.dpToPx(5, this));
        binding.carousel.setCarouselPaddingBottom(Utils.dpToPx(10, this));
        binding.carousel.setCarouselGravity(CarouselGravity.CENTER);
        binding.carousel.setCarouselType(CarouselType.SHOWCASE);
        binding.carousel.setScalingFactor(0.4f);

//        carousel.setAutoWidthFixing(true);
//        carousel.setScalingFactor(4f);

        List<CarouselItem> list = new ArrayList<>();

        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );

        binding.carousel.setData(list);


        binding.carousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy, int position, @org.jetbrains.annotations.Nullable CarouselItem carouselItem) {
                // ...
            }

            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState, int position, @org.jetbrains.annotations.Nullable CarouselItem carouselItem) {
                // ...
            }
        });


        adapter = new MainAdapter();

        binding.carousel.setCarouselListener(new CarouselListener() {
            @Nullable
            @Override
            public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {

            }

            @Override
            public void onClick(int i, @NonNull CarouselItem carouselItem) {
                globalFunctions.showToast("Click en " + carouselItem.getImageUrl());
            }

            @Override
            public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

            }
        });

        CircleIndicator2 indicator = binding.customIndicator;
        binding.carousel.setIndicator(indicator);

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
//            globalFunctions.showToast("HOA CON DAGGER 2");
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