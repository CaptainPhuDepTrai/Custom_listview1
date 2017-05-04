package vn.edu.eiu.custom_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_manv, edt_tennv;
    RadioButton rad_nam, rad_nu;
    Button btn_nhap, btn_xoa;
    ListView ds_nhan_vien;

    MyAdapter adapter;
    ArrayList<Employee> arr_list_employee = new ArrayList<Employee>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_custom_list_view);

        khoi_tao_control();

        Employee e = new Employee("001", "Teo ca", true);
        Employee e1 = new Employee("002", "Thị Nở", false);
        arr_list_employee.add(e);
        arr_list_employee.add(e1);
        //khởi tạo adapter
        adapter = new MyAdapter(MainActivity.this, arr_list_employee);
        //gán adapter cho listview
        ds_nhan_vien.setAdapter(adapter);
        //gán sự kiện click cho button nhập
        btn_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xu_ly_nhap();
            }
        });
        //gán sự kiện cho button xóa
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xu_ly_xoa();
            }
        });
    }

    public void khoi_tao_control() {
        edt_manv = (EditText) findViewById(R.id.edt_manv);
        edt_tennv = (EditText) findViewById(R.id.edt_tennv);
        rad_nam = (RadioButton) findViewById(R.id.rad_nam);
        rad_nu = (RadioButton) findViewById(R.id.rad_nu);
        btn_nhap = (Button) findViewById(R.id.btn_nhap);
        btn_xoa = (Button) findViewById(R.id.btn_xoa);
        ds_nhan_vien = (ListView) findViewById(R.id.listView);
    }

    public void xu_ly_nhap() {
        String manv = edt_manv.getText().toString().trim();
        if (manv.length() == 0) {
            Toast.makeText(MainActivity.this, "Ma NV k rỗng", Toast.LENGTH_SHORT).show();
            edt_manv.requestFocus();
            return;
        }
        String tennv = edt_tennv.getText().toString().trim();
        if (tennv.length() == 0) {
            Toast.makeText(MainActivity.this, "Ten NV k rỗng", Toast.LENGTH_SHORT).show();
            edt_tennv.requestFocus();
            return;
        }
        if (rad_nam.isChecked() == false && rad_nu.isChecked() == false) {
            Toast.makeText(MainActivity.this, "Chưa chọn giới tính", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean gender = true;
        if (rad_nu.isChecked())
            gender = false;

        //thêm vào ArrayList
        Employee employee = new Employee(manv, tennv, gender);

        arr_list_employee.add(employee);
        //update new data
        adapter.notifyDataSetChanged();
        //reset control
        edt_manv.setText("");
        edt_tennv.setText("");
        rad_nam.setChecked(false);
        rad_nu.setChecked(false);
        edt_manv.requestFocus();
    }

    public void xu_ly_xoa() {
        //duyệt ds theo thứ tự ngược
        for (int i = ds_nhan_vien.getChildCount() - 1; i >= 0; i--) {
            //xử lý dòng thứ i,
            //mỗi dòng i gồm 3 thành phần: ImageView, TextView, Checkbox
            View v = ds_nhan_vien.getChildAt(i);
            //kiểm tra checkbox xóa
            CheckBox ckb_xoa = (CheckBox) v.findViewById(R.id.ckb_xoa);
            //nếu bằng true  thì xử lý xóa
            if (ckb_xoa.isChecked()) {
                arr_list_employee.remove(i);
                //update data
                adapter.notifyDataSetChanged();
            }

        }
    }
}
