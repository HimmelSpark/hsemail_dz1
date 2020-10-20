package com.example.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import com.example.myapplication.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        adapter = new MyAdapter(1, 100, myDataSet);
        recyclerView = findViewById(R.id.my_recycler_view);
        int orientation = this.getResources().getConfiguration().orientation;
        int spanCount = orientation == ORIENTATION_PORTRAIT ? 3 : 4;
        layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        final Button button = findViewById(R.id.btnAdd);
        button.setOnClickListener(view -> {
            RecyclerView.Adapter<MyAdapter.MyViewHolder> adapter = recyclerView.getAdapter();
            Optional.ofNullable(adapter)
                    .map(RecyclerView.Adapter::getItemCount)
                    .map(number -> addToRecycler(adapter, ++number))
                    .orElse(null);

        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("myDataSet", (ArrayList<Integer>) myDataSet);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        ArrayList<Integer> myDataSet = savedInstanceState.getIntegerArrayList("myDataSet");
        this.myDataSet = Optional.ofNullable(myDataSet)
                .orElseGet(ArrayList::new);
    }

    private Integer addToRecycler(RecyclerView.Adapter<MyAdapter.MyViewHolder> adapter, Integer number) {
        myDataSet.add(number);
        adapter.notifyDataSetChanged();
        return number;
    }

}