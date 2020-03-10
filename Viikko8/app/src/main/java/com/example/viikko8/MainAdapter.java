package com.example.viikko8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Bottle> mbottles;

    public MainAdapter(ArrayList<Bottle> bottles) {
        mbottles = bottles;

    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
         holder.mBottle.setText(mbottles.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mbottles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mBottle;
        public ViewHolder(View itemView) {
            super(itemView);

            mBottle = itemView.findViewById(R.id.row_bottle);
        }
    }
}
