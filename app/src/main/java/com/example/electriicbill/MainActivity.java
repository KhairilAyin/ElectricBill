package com.example.electriicbill;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button btnCalc;
    Button btnclear;
    EditText EtKwh;
    EditText EtRebate;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnCalc = findViewById(R.id.btnCalc);
        btnclear = findViewById(R.id.btnclear);
        EtKwh = findViewById(R.id.EtKwh);
        EtRebate = findViewById(R.id.EtRebate);
        tvOutput = findViewById(R.id.tvOutput);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Calculate button functionality
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get input values
                    double calcKwh = Double.parseDouble(EtKwh.getText().toString());
                    double calcReb = Double.parseDouble(EtRebate.getText().toString());

                    // Validate rebate percentage
                    if (calcReb < 0 || calcReb > 5) {
                        showAlert("Invalid Rebate", "Rebate percentage must be between 0 and 5.");
                        return;
                    }

                    // Calculate electricity bill
                    double calcBill = calculateBill(calcKwh, calcReb);

                    // Display the result
                    tvOutput.setText(String.format("Total Bill: RM %.2f", calcBill));
                } catch (NumberFormatException e) {
                    tvOutput.setText("Invalid input. Please enter valid numbers.");
                }
            }
        });

        // Clear button functionality
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EtKwh.setText("");
                EtRebate.setText("");
                tvOutput.setText("");
            }
        });
    }

    // Method to calculate the bill
    private double calculateBill(double kwh, double rebate) {
        double bill = 0.0;

        // Apply rates based on blocks
        if (kwh > 0 && kwh <= 200) {
            bill += kwh * 21.8;
            bill = bill/100;
        }

        else if (kwh > 200 && kwh <= 300) {
            bill += (kwh - 200) * 33.4 + 4360;
            bill = bill/100;
        }

        else if (kwh > 300 && kwh <= 600) {
            bill += (kwh - 300) * 51.6 + 7700;
            bill = bill/100;
        }

        else if (kwh > 600) {
            bill += (kwh - 600) * 54.6 + 23180;
            bill = bill/100;
        }

        // Apply rebate
        bill -= bill * (rebate / 100.0);

        return bill;
    }

    // Method to show an alert dialog
    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            // Create an Intent to start the AboutActivity
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}