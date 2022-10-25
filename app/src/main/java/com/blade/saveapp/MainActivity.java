package com.blade.saveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tx_firstname;
    EditText et_firstname;
    Button bt_save, bt_retrieve, bt_exit;

    SharedPreferences myPreference;

    String myFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPreference = getSharedPreferences("mySharedPreference", MODE_PRIVATE);
        tx_firstname = findViewById(R.id.tx_firstname);
        et_firstname = findViewById(R.id.et_firstname);
        bt_save = findViewById(R.id.bt_save);
        bt_retrieve = findViewById(R.id.bt_retrieve);
        bt_exit = findViewById(R.id.bt_exit);

        bt_save.setOnClickListener(v -> {
            String myValue = et_firstname.getText().toString();
            SharedPreferences.Editor myEditor = myPreference.edit();

            myEditor.putString("myNameFirst", myValue);
            myEditor.apply();

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });

        bt_retrieve.setOnClickListener(v -> {
            myFirstName = myPreference.getString("myNameFirst", "no_value");

            tx_firstname.setText(myFirstName);
        });

        bt_exit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Exit App");
            builder.setMessage("Do you want to exit the app?");
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.setPositiveButton("Exit", (dialogInterface, i) -> finish());

            builder.create();
            builder.show();
        });


    }

    @Override
    protected void onResume() {
        tx_firstname = findViewById(R.id.tx_firstname);
        super.onResume();

        myFirstName = myPreference.getString("myNameFirst", "no_value");

        tx_firstname.setText(myFirstName);
    }


}