package com.example.viikko9;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Cinema> mCinList;

    public MainAdapter(ArrayList<Cinema> cinList) {
        mCinList = cinList;
    }


    @Override
    public MainAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.cinema_id.setText(mCinList.get(position).getId() + ", \t\t" + mCinList.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return mCinList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cinema_id;
        public TextView cinema_name;
        public ViewHolder(View itemView) {
            super(itemView);

            cinema_id = itemView.findViewById(R.id.cinema_id);
        }
    }


}
