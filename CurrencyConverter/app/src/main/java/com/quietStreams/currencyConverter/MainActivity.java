package com.quietStreams.currencyConverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {
        EditText nairaField = (EditText) findViewById(R.id.nairaField);
        Button button = (Button) findViewById(R.id.button);

        Double nairaAmount = Double.parseDouble(nairaField.getText().toString());
        Double dollarAmount = nairaAmount * 0.0024;

        Toast.makeText(getApplicationContext(), "$" + String.format("%.2f", dollarAmount), Toast.LENGTH_LONG).show();

    }

}