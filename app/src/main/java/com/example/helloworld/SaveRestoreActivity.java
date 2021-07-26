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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SaveRestoreActivity extends AppCompatActivity {
    private int mLession = 1; // Lưu tên bài hiện tại
    TextView message;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_save_restore);
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
}
