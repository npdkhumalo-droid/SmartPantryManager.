package com.smartpantrymanager.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;
import com.smartpantrymanager.adapters.PantryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartpantrymanager.database.DBhelper;

import java.util.List;

/** Pantry List screen (READ + entry point for CREATE / UPDATE / DELETE). */
public class MainActivity implements PantryAdapter.Listener {

    private DBhelper db;
    private PantryAdapter adapter;
    private TextView tvEmpty;

    public void onCreate() {
        onCreate(null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        PantryAdapter.Listener.super.clone(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBhelper(this);
        tvEmpty = findViewById(R.id.tvEmpty);

        RecyclerView rv = findViewById(R.id.rvPantry);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PantryAdapter(this);
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddEditActivity.class)));

        setupNav(R.id.nav_pantry);
    }

    /** Refresh from the database every time the screen becomes visible again. */
    @Override
    protected void onResume() {
        super.onResume();
        List<Models.PantryItem> items = db.getAllItems();
        adapter.setItems(items);
        tvEmpty.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onEdit(Models.PantryItem item) {
        Intent i = new Intent(this, AddEditActivity.class);
        i.putExtra("item_id", item.id); // pass data to the next screen
        startActivity(i);
    }

    @Override
    public void onDelete(final Models.PantryItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Delete ingredient?")
                .setMessage("Remove " + item.name + " from your pantry?")
                .setPositiveButton("Delete", (d, w) -> {
                    db.deleteItem(item.id);
                    Toast.makeText(this, item.name + " deleted", Toast.LENGTH_SHORT).show();
                    onResume();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void clone(Bundle savedInstanceState) {

    }
}
