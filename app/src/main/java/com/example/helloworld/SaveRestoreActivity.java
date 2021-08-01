package com.example.helloworld;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SaveRestoreActivity extends AppCompatActivity {
    private int mLession = 1; // Lưu tên bài hiện tại
    TextView message;
    Button button;

    CheckBox int_id, double_id, string_id, all;
    Button test, hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_save_restore);

        ToggleButton toggleButton1 = findViewById(R.id.toggle1);
        ToggleButton toggleButton2 = findViewById(R.id.toggle2);
        toggleButton1.setChecked(false);
        toggleButton2.setChecked(true);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.toggle1:
                        Toast.makeText(SaveRestoreActivity.this, "Nút 1: " + compoundButton.isChecked(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.toggle2:
                        Toast.makeText(SaveRestoreActivity.this, "Nút 2: " + compoundButton.isChecked(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        toggleButton1.setOnCheckedChangeListener(listener);
        toggleButton2.setOnCheckedChangeListener(listener);

        init();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SaveRestoreActivity.this, "Hi!", Toast.LENGTH_SHORT).show();
            }
        });
        String htmlcontent = "<h1>Đây là HTML</h1><ul><li>Thẻ UL/LI</li><li>Thẻ UL/LI</li></ul>" +
                "<p>Dòng <font color=\"red\">chữ</font> trong <big><b>thẻ p</b></big><p>" +
                "<a href=\"...\">Bấm vào link</a>" +
                "<strike>Gạch ngang</strike>";
        TextView mytextview = findViewById(R.id.textview);
        mytextview.setText(android.text.Html.fromHtml(htmlcontent));
        // Trang trí nội dung TextView với SpannableString
        String noidung =
                "SpannableString\n"
                + "Chữ đậm\n"
                + "Gạch chận\n"
                + "Nghiêng\n"
                + "Kẻ ngang\n"
                + "Màu sắc\n"
                + "12AM\n"
                + "Click Me\n"
                + "URL\n"
                ;
        SpannableString noidungspanned = new SpannableString(noidung);
        noidungspanned.setSpan(new RelativeSizeSpan(2f), 0, 15, 0);
        noidungspanned.setSpan(new StyleSpan(Typeface.BOLD), 16, 23, 0);
        noidungspanned.setSpan(new UnderlineSpan(), 24, 33, 0);
        noidungspanned.setSpan(new StyleSpan(Typeface.ITALIC), 34, 42, 0);
        noidungspanned.setSpan(new StrikethroughSpan(), 42, 50, 0);
        noidungspanned.setSpan(new BackgroundColorSpan(Color.GREEN), 51, 59, 0);
        noidungspanned.setSpan(new ForegroundColorSpan(Color.RED), 34, 51, 0);
        noidungspanned.setSpan(new SuperscriptSpan(), 61, 64, 0);
        noidungspanned.setSpan(new RelativeSizeSpan(.9f), 61, 64, 0);
        noidungspanned.setSpan(new URLSpan("https://xuanthulab.net"), 64, 73, 0);
        noidungspanned.setSpan(new AlignmentSpan() {
            @Override
            public Layout.Alignment getAlignment() {
                return Layout.Alignment.ALIGN_OPPOSITE;
            }
        }, 0, 15, 0);
        noidungspanned.setSpan(new DrawableMarginSpan(
                ContextCompat.getDrawable(this, R.mipmap.ic_launcher), 30), 34, 42, 0);
        noidungspanned.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(mytextview.getContext(),
                        "Clicked me", Toast.LENGTH_SHORT).show();
            }
        }, 73, 76, 0);
        // Gọi setMovementMethod cho phép click vào URL
        mytextview.setMovementMethod(LinkMovementMethod.getInstance());
        mytextview.setText(noidungspanned);
    }
    // Hiện thị dòng chữ tên bài hiện tại
    void setLession()
    {
        message.setText("Bai hoc: " + mLession);
    }
    CompoundButton.OnCheckedChangeListener m_listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton == all)
            {
                detachListener();
                int_id.setEnabled(!b);
                double_id.setEnabled(!b);
                string_id.setEnabled(!b);
                int_id.setChecked(b);
                double_id.setChecked(b);
                string_id.setChecked(b);
                attachListener();
            } else {
                Toast.makeText(compoundButton.getContext(), compoundButton.getText() + " | " + compoundButton.isChecked(), Toast.LENGTH_SHORT).show();
            }
        }
    };
    // Gán listener vào checkbox
    void attachListener()
    {
        int_id.setOnCheckedChangeListener(m_listener);
        double_id.setOnCheckedChangeListener(m_listener);
        string_id.setOnCheckedChangeListener(m_listener);
        all.setOnCheckedChangeListener(m_listener);
    }
    // Bỏ các listener khỏi checkbox
    void detachListener()
    {
        int_id.setOnCheckedChangeListener(null);
        double_id.setOnCheckedChangeListener(null);
        string_id.setOnCheckedChangeListener(null);
        all.setOnCheckedChangeListener(null);
    }
    public void init()
    {
        int_id = findViewById(R.id.int_id);
        double_id = findViewById(R.id.double_id);
        string_id = findViewById(R.id.string_id);
        all = findViewById(R.id.all);
        attachListener();
        test = findViewById(R.id.test);
        hint = findViewById(R.id.hint);
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detachListener();
                int_id.setChecked(false);
                double_id.setChecked(false);
                string_id.setChecked(true);
                all.setChecked(false);
                attachListener();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                if (!int_id.isChecked() && !double_id.isChecked() && string_id.isChecked())
                    msg = "Đúng, chúc mừng";
                else
                    msg = "Sai rồi";
                Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
