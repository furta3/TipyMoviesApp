package com.example.tipymovies.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tipymovies.R;
import com.example.tipymovies.model.JoinTopTen;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TopTenTriviaAdapter extends RecyclerView.Adapter<TopTenTriviaAdapter.TopTenViewHolder> implements View.OnClickListener{
    private List<JoinTopTen> topTens;
    private int rowLayout;
    private Context context;
    private View.OnClickListener listener;

    public TopTenTriviaAdapter(List<JoinTopTen> topTens, int rowLayout, Context context) {
        this.topTens = topTens;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public static class TopTenViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView username;
        TextView data;

        public TopTenViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            username = (TextView) v.findViewById(R.id.username);
            data = (TextView) v.findViewById(R.id.date);
        }
    }
    @Override
    public TopTenTriviaAdapter.TopTenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        view.setOnClickListener(this);
        return new TopTenTriviaAdapter.TopTenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TopTenViewHolder holder, int position) {
        holder.username.setText(topTens.get(position).getUsername());
        holder.data.setText(topTens.get(position).getPuntos());
    }


    @Override
    public int getItemCount() {
        return topTens.size();
    }
}
