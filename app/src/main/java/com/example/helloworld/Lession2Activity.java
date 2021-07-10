package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lession2Activity extends AppCompatActivity {
    final int RESULT_UPDATE_ACTIVITY = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lession2_activity);
        // Các khởi tạo khác nếu cần thiết
        TextView textView = findViewById(R.id.noidung);
        Button editbutton = findViewById(R.id.editbutton);
        // Tạo listener cho sự kiện click
        View.OnClickListener listenerEditbuton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Gán context và tên lớp Activity cần chạy
                intent.setClass(view.getContext(), UpdateActivity.class);
                // Nếu có truyền tham số cho Activity
                intent.putExtra("noidung", textView.getText());
                // Gửi intent cho hệ thống android để kích hoạt Activity
                startActivityForResult(intent, RESULT_UPDATE_ACTIVITY);
                // Muốn Activity thứ nhất kết thúc thì thêm finish()
                Toast.makeText(view.getContext(), "Vừa bấm vào Edit", Toast.LENGTH_LONG).show();
            }
        };
        // Gán listener cho editbutton
        editbutton.setOnClickListener(listenerEditbuton);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_UPDATE_ACTIVITY:
                String noidung = data.getStringExtra("noidung");
                TextView textView = findViewById(R.id.noidung);
                textView.setText(noidung);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Code to save data
    }
}
