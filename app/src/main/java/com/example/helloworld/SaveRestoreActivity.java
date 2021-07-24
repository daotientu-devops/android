package com.example.helloworld;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SaveRestoreActivity extends AppCompatActivity {
    private int mLession = 1; // Lưu tên bài hiện tại
    TextView message;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_save_restore);
        TextView mytextview = findViewById(R.id.textview);
    }

    // Hiện thị dòng chữ tên bài hiện tại
    void setLession()
    {
        message.setText("Bai hoc: " + mLession);
    }
}
