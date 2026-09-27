import android.os.Bundle;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    SwitchCompat swAlerts = findViewById(R.id.swAlerts);
    BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

    // Example: toggle alert switch
    swAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if (!isChecked) {
            // Disable alerts
        } else {
            // Enable alerts
        }
    });

    // Example: handle bottom nav clicks
    bottomNav.setOnItemSelectedListener(item -> {
        if (item.getItemId() == R.id.nav_home) {
            // Navigate to home
            return true;
        } else if (item.getItemId() == R.id.nav_settings) {
            // Already in settings
            return true;
        }
        return false;
    });
}


