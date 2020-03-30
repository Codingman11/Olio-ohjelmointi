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
         holder.bottle_name.setText(mbottles.get(position).getName() + ",\t" + mbottles.get(position).getSize() + "l,\t" + mbottles.get(position).getPrice() + "€");

    }

    @Override
    public int getItemCount() {
        return mbottles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView bottle_name;
        public TextView bottle_size;
        public ViewHolder(View itemView) {
            super(itemView);

            bottle_name = itemView.findViewById(R.id.bottle_name);

        }
    }
}
