package com.example.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String msg = "Android ";
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBHelper mydb;
    TextView name;
    TextView phone;
    TextView email;
    TextView street;
    TextView place;
    int id_To_Update = 0;

    ImageView imageView;
    TextView textView;

    ArrayList<Product> listProduct;
    ProductListViewAdapter productListViewAdapter;
    ListView listViewProduct;

    private FloatingActionButton fab;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private boolean isFABOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fab = (FloatingActionButton)findViewById(R.id.fab);
        this.fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        this.fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        this.fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        this.fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });
    }
    private void showFABMenu() {
        isFABOpen = true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }
    private void closeFABMenu() {
        isFABOpen = false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateContact(id_To_Update, name.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        street.getText().toString(),
                        place.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertContact(name.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        street.getText().toString(),
                        place.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not done", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayContact.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    /**
     * Called when the activity is about to become visible
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /**
     * Called when the activity has become visible
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /**
     * Called when the activity is no longer visible
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /**
     * Called just before the activity is destroyed
     */
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }
}

class ProductListViewAdapter extends BaseAdapter {
    // Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<Product> listProduct;
    public ProductListViewAdapter(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        // Trả về tổng số phần tử, nó được gọi bởi ListView
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        // Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        // có chỉ số position trong listProduct
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Trả về một ID của phần
        return listProduct.get(position).productID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        // View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        // Nếu null cần tạo mới
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.product_view, null);
        } else
            viewProduct = convertView;
        // Bind dữ liệu phần tử vào View
        Product product = (Product) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.idproduct)).setText(String.format("ID = %d", product.productID));
        ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText(String.format("Tên SP: %s", product.name));
        ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText(String.format("Giá: %d$", product.price));
        return viewProduct;
    }
}