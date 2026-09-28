package com.example.smartpantry;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/** Shared bottom-navigation behaviour. Uses explicit Intents to move between screens. */
public abstract class BaseActivity extends AppCompatActivity {

    protected void setupNav(final int selected) {
        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setSelectedItemId(selected);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == selected) return true;
            Intent i;
            if (id == R.id.nav_pantry) {
                i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            } else if (id == R.id.nav_suggest) {
                i = new Intent(this, SuggestedActivity.class);
            } else {
                i = new Intent(this, SettingsActivity.class);
            }
            startActivity(i);
            if (selected != R.id.nav_pantry) finish();
            return true;
        });
    }
}
