package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    EditText editText1;
    Button button1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText1.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("MESSAGE", message);
                setResult(2, intent);
                finish(); // finishing activity
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
