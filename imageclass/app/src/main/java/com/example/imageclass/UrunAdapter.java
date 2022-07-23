package com.example.imageclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageclass.databinding.ActivityFeedBinding;
import com.example.imageclass.databinding.RecyclerRowBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UrunAdapter extends RecyclerView.Adapter<UrunAdapter.UrunHolder> {


    public UrunAdapter(ArrayList<Urun> urunArrayList) {
        this.urunArrayList = urunArrayList;
    }

    private ArrayList<Urun> urunArrayList;



    @NonNull
    @Override
    public UrunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return  new UrunHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UrunHolder holder, int position) {
        holder.recyclerRowBinding.recyclerViewProductNameText.setText(urunArrayList.get(position).name);
        holder.recyclerRowBinding.recyclerViewStockAmountText.setText(urunArrayList.get(position).stock);
        holder.recyclerRowBinding.recyclerViewPriceText.setText(urunArrayList.get(position).price);
    }
    public void removeUrun(Urun urun){
        urunArrayList.remove(urun);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return urunArrayList.size();
    }

    class UrunHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;

        public UrunHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;
        }
    }
}
