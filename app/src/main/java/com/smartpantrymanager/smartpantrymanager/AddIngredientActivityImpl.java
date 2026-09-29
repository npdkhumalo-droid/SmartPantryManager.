import android.widget.LinearLayout;
import android.widget.ScrollView;

<?xml version="1.0" encoding="utf-8"?>

<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent">

    <LinearLayout
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="vertical"
android:padding="16dp">

        <ImageView
android:id="@+id/imgIngredient"
android:layout_width="150dp"
android:layout_height="150dp"
android:layout_gravity="center"
android:src="@drawable/tomato"
android:scaleType="centerCrop"/>

        <EditText
android:id="@+id/etName"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Ingredient Name"/>

        <EditText
android:id="@+id/etQuantity"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Quantity"/>

        <EditText
android:id="@+id/etUnit"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Unit"/>

        <Button
android:id="@+id/btnSave"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="Save Ingredient"/>

    </LinearLayout>

</ScrollView>
