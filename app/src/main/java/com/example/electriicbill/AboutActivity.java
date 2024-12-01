package com.example.electriicbill;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutActivity extends AppCompatActivity {

    Button githubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Handle window insets (status bar and navigation bar padding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            int left = insets.getInsets(WindowInsetsCompat.Type.systemBars()).left;
            int top = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top;
            int right = insets.getInsets(WindowInsetsCompat.Type.systemBars()).right;
            int bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setPadding(left, top, right, bottom);
            return insets;
        });

        // Set up the action bar (back button)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize views
        githubButton = findViewById(R.id.githubButton);
        ImageView imageView = findViewById(R.id.imageView3);
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView3);
        TextView textView3 = findViewById(R.id.textView2);

        // Set content for the views
        textView1.setText("Khairil Hazmie bin Abdul Razak");
        textView2.setText("2022697564");
        textView3.setText("RCDCS2515A");
        imageView.setImageResource(R.drawable.about);

        // Set up button click listener
        githubButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KhairilAyin/ElectricBill"));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), "No app found to open GitHub link", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the Up button click (navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
