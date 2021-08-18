package com.example.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTest;
    CoordinatorLayout.LayoutParams txtTestLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        TextView txtTest = findViewById(R.id.txtTest);
        txtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar snackbar = Snackbar.make(view, ((TextView)view).getText(), Snackbar.LENGTH_SHORT);
                //snackbar.show();
                FirstBottomSheetDialogFragment myDialog = new FirstBottomSheetDialogFragment();
                FragmentManager fm = getSupportFragmentManager();
                myDialog.show(fm, "FirstBottomSheetDialogFragment");
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
    }
    BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_HIDDEN:
                    // View bị ẩn
                    break;
                case BottomSheetBehavior.STATE_EXPANDED:
                    // View mở rộng lên
                    Toast.makeText(bottomSheet.getContext(), "Mở rộng", Toast.LENGTH_SHORT).show();
                    break;
                case BottomSheetBehavior.STATE_DRAGGING:
                    // Bắt đầu kéo View
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                    // View thu gọn lại
                    Toast.makeText(bottomSheet.getContext(), "Thu gọn", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            // Vị trí tương đối của View khi trượt, slideOffset = -1 đến 1
            // Khi nhận từ 0 -> 1 thì sheet đang chuyển từ thu hẹp sang mở rộng
            // code sau sẽ làm mờ mainlayout khi kéo sheet lên và ngược lại
            float alpha = 1 - slideOffset;
            View mainlayout = findViewById(R.id.mainlayout);
            if (alpha >= 0.5)
                mainlayout.animate().alpha(alpha).start();
        }
    };
    public static class FirstBottomSheetDialogFragment extends BottomSheetDialogFragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Tạo View của Fragment
            return inflater.inflate(R.layout.fragment_bottom_sheet, container);
        }
    }
}
