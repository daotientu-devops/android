package com.example.helloworld;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
        // Lấy đối tượng TextView và Button trong layout
        message = findViewById(R.id.message);
        button = findViewById(R.id.next_lesson);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // bấm vào thì bài học tăng 1 và đặt màu nền của nút bấm thành GREEN
                mLession++;
                setLession();
                button.setBackgroundColor(Color.GREEN);
            }
        });
        setLession();
    }

    // Hiện thị dòng chữ tên bài hiện tại
    void setLession()
    {
        message.setText("Bai hoc: " + mLession);
    }
}
