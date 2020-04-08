package com.example.viikko8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Bottle> mbottles;

    public MainAdapter(ArrayList<Bottle> bottles) {
        mbottles = bottles;

    }


    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
         holder.BottleText.setText(mbottles.get(position).getName() + ": " + mbottles.get(position).getSize() +"l: " + mbottles.get(position).getPrice() + "€");

    }

    @Override
    public int getItemCount() {
        return mbottles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView BottleText;
        public ViewHolder(View itemView) {
            super(itemView);

            BottleText = itemView.findViewById(R.id.row_bottle);
        }
    }
}
