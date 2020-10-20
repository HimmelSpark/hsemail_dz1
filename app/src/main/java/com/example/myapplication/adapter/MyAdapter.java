package com.example.myapplication.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Getter;
import lombok.Setter;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Integer> myDataset;

    public MyAdapter(Integer a, Integer b, List<Integer> myDataset) {
        this.myDataset = myDataset;
        IntStream.rangeClosed(a, b).forEach(myDataset::add);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView child = (TextView)((LinearLayout) holder.view).getChildAt(0);
        Integer number = myDataset.get(position);
        child.setText(String.valueOf(number));
        child.setTextColor(number % 2 == 0 ? Color.parseColor("#FF0000") : Color.parseColor("#0000FF"));
    }

    @Override
    public int getItemCount() {
        return Optional.ofNullable(myDataset).map(List::size).orElse(0);
    }

    @Getter
    @Setter
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;

        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

}
