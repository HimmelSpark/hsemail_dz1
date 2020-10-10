package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.adapter.MyAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<MyAdapter.MyViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter(1, 100);
        recyclerView = findViewById(R.id.my_recycler_view);
        int orientation = this.getResources().getConfiguration().orientation;
        int spanCount = orientation == ORIENTATION_PORTRAIT ? 3 : 4;
        layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}