package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        EditText noidung_edit = findViewById(R.id.noidung_edit);
        // Sử dụng intent chứa kết quả trả về
        Intent intent = getIntent();
        String noidung = intent.getStringExtra("noidung");
        noidung_edit.setText(noidung);
        Button ok_button = findViewById(R.id.ok_button);
        // Bắt sự kiện khi bấm nút Save
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultData();
                finish(); // Kết thúc Activity (sẽ quay trở về Activity trước nó)
                Toast.makeText(v.getContext(), "Save", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setResultData()
    {
        Intent data = new Intent();
        EditText editText = findViewById(R.id.noidung_edit);
        String noidung = editText.getText().toString();
        data.putExtra("noidung", noidung);
        int code_result = 1;
        setResult(code_result, data);
    }
}
