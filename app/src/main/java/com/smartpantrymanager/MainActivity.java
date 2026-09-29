package com.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartpantrymanager.adapter.PantryAdapter;
import com.smartpantrymanager.database.DatabaseHelper;
import com.smartpantrymanager.model.PantryItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PantryAdapter.Listener {
    private DatabaseHelper db;
    private PantryAdapter adapter;
    private TextView tvTotal, tvExpiring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        tvTotal = findViewById(R.id.tvTotalItems);
        tvExpiring = findViewById(R.id.tvExpiringSoon);

        RecyclerView rv = findViewById(R.id.rvPantry);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PantryAdapter(this);
        rv.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> startActivity(new Intent(this, AddIngredientActivity.class)));
        findViewById(R.id.btnRecipes).setOnClickListener(v -> startActivity(new Intent(this, SuggestedRecipesActivity.class)));
        findViewById(R.id.btnSettings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume(); // runs again after Save, so the list is always fresh
        refresh();
    }

    private void refresh() {
        List<PantryItem> items = db.getAllPantryItems();
        adapter.setItems(items);
        int expiring = 0;
        for (PantryItem p : items) if (p.isExpiringSoon()) expiring++;
        tvTotal.setText(String.valueOf(items.size()));
        tvExpiring.setText(String.valueOf(expiring));
    }

    @Override
    public void onEdit(PantryItem item) {
        Intent i = new Intent(this, AddIngredientActivity.class);
        i.putExtra(AddIngredientActivity.EXTRA_ID, item.id);
        startActivity(i);
    }

    @Override
    public void onDelete(PantryItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Delete ingredient")
                .setMessage("Remove " + item.name + " from your pantry?")
                .setPositiveButton("Delete", (d, w) -> {
                    db.deletePantryItem(item.id);
                    refresh();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
