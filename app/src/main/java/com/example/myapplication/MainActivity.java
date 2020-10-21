package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<MyAdapter.MyViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Integer> myDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            this.myDataSet = initializeRecycler(null);
        } else {
            this.myDataSet = Optional.of(savedInstanceState)
                    .map(s -> s.getIntegerArrayList("myDataSet"))
                    .map(this::initializeRecycler)
                    .orElseGet(() -> initializeRecycler(null));
        }
        final Button button = findViewById(R.id.btnAdd);
        button.setOnClickListener(view -> {
            RecyclerView.Adapter<MyAdapter.MyViewHolder> adapter = recyclerView.getAdapter();
            Optional.ofNullable(adapter)
                    .map(RecyclerView.Adapter::getItemCount)
                    .map(number -> addToRecycler(adapter, ++number))
                    .orElse(null);
        });
    }

    private ArrayList<Integer> initializeRecycler(List<Integer> initialList) {
        if (initialList == null) {
            initialList = new ArrayList<>(100);
            IntStream.rangeClosed(1, 100).forEach(initialList::add);
        }
        adapter = new MyAdapter(initialList);
        recyclerView = findViewById(R.id.my_recycler_view);
        int orientation = this.getResources().getConfiguration().orientation;
        int spanCount = orientation == ORIENTATION_PORTRAIT ? 3 : 4;
        layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return (ArrayList<Integer>) initialList;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("myDataSet", (ArrayList<Integer>) myDataSet);
    }

    private Integer addToRecycler(RecyclerView.Adapter<MyAdapter.MyViewHolder> adapter, Integer number) {
        myDataSet.add(number);
        adapter.notifyDataSetChanged();
        return number;
    }

}