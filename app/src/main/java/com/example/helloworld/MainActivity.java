package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    int themeIdCurrent;

    Display display;
    TextView huongmanhinh, gocnghieng;
    OrientationEventListener myOrientationEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        huongmanhinh = findViewById(R.id.huongmanhinh);
        gocnghieng = findViewById(R.id.gocnghieng);

        InfoScreen();
        ListenerRotate();

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
        /**
         * Tạo ra 1 Broadcast Intent sau đó gửi đi (lan truyền) trong ứng dụng
         * Action ==> Intent ==> Send broadcast
         */
        String actionName = "my-first-broadcastintent";
        Intent intent = new Intent(actionName);
        // Thiết lập tên để cho Receiver nhận được thì biết đó là loại Intent
        intent.setAction(actionName);
        // Dữ liệu gắn vào Intent thiết lập bằng putExtra với định dạng (tên, dữ liệu)
        // Dữ liệu là các kiểu cơ bản Int, String, ... hoặc các loại đối tượng lớp kế thừa từ serializable
        intent.putExtra("dataname", "Hello, How are you?");
        // Thực hiện lan truyền Intent trong hệ thống
        sendBroadcast(intent);

        // Đọc ID theme đã lưu, nếu chưa lưu thì dùng R.style.MyAppTheme
        SharedPreferences locationPref = getApplicationContext().getSharedPreferences("MainActivity", MODE_PRIVATE);
        themeIdCurrent = locationPref.getInt("themeid", R.style.MyAppTheme);
        // Thiết lập theme cho Activity
        setTheme(themeIdCurrent);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển đổi theme
                themeIdCurrent = themeIdCurrent == R.style.MyAppTheme ? R.style.AppTheme : R.style.MyAppTheme;
                // Lưu lại theme ID
                SharedPreferences locationpref = getApplicationContext().getSharedPreferences("MainActivity", MODE_PRIVATE);
                SharedPreferences.Editor spedit = locationpref.edit();
                spedit.putInt("themeid", themeIdCurrent);
                spedit.apply();
                // Tạo lại activity để áp dụng theme mới đổi
                recreate();
            }
        });
    }
    // Listener nhận sự kiện thay đổi góc nghiêng điện thoại
    void ListenerRotate() {
        myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                gocnghieng.setText("Góc nghiêng: " + orientation + "o");
                InfoScreen();
            }
        };
        myOrientationEventListener.enable();
    }
    // Xác định màn hình PORTRAIT, LANDSCAPE
    void InfoScreen() {
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                huongmanhinh.setText("Màn hình LAYOUT đứng: 0o");
                break;
            case Surface.ROTATION_90:
                huongmanhinh.setText("Màn hình LAYOUT ngang: 90o");
                break;
            case Surface.ROTATION_180:
                huongmanhinh.setText("Màn hình LAYOUT ngang: 180o");
                break;
            case Surface.ROTATION_270:
                huongmanhinh.setText("Màn hình LAYOUT đứng: 270o");
                break;
        }
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
