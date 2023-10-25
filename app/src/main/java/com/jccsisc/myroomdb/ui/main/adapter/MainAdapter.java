package com.jccsisc.myroomdb.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.jccsisc.myroomdb.databinding.ItemProfessorBinding;
import com.jccsisc.myroomdb.ui.crudprofessor.model.ProfessorModel;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.iu.main.adapter
 * Created by Julio Cesar Camacho Silva on 28/08/23
 */
public class MainAdapter extends ListAdapter<ProfessorModel, MainAdapter.ViewHolderProfessors> {

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ProfessorModel professorModel);
    }

    public static final DiffUtil.ItemCallback<ProfessorModel> DIFF_CALBACK = new DiffUtil.ItemCallback<ProfessorModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProfessorModel oldItem, @NonNull ProfessorModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProfessorModel oldItem, @NonNull ProfessorModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MainAdapter() {
        super(DIFF_CALBACK);
    }

    @NonNull
    @Override
    public ViewHolderProfessors onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProfessorBinding binding = ItemProfessorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderProfessors(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProfessors holder, int position) {
        ProfessorModel professorModel = getItem(position);
        holder.bind(professorModel);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolderProfessors extends RecyclerView.ViewHolder {

        ItemProfessorBinding binding;

        public ViewHolderProfessors(@NonNull ItemProfessorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProfessorModel professorModel) {
            binding.tvProfessorName.setText(professorModel.getName());
            binding.tvProfessoEmail.setText(professorModel.getEmail());

            binding.getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(professorModel));
        }
    }
}
