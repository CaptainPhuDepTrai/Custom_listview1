package vn.edu.eiu.custom_listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 5/3/2017.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Employee> list;

    public MyAdapter(Context c, ArrayList<Employee> li){
        context = c;
        list = li;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Employee getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //tạo ddtuong ListView_row minh họa cho 1 dòng trong listivew
        ListView_Row lv_row;
        //check xem ddtuong được khởi tạo chưa
        if (convertView == null) {
            //thiết lập inflater
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            //ép convertView thành Layout ListView
            convertView = inflater.inflate(R.layout.list_view_item,null);

            //lấy các control từ Layout ListView item
            lv_row = new ListView_Row();
            lv_row.hinh = (ImageView) convertView.findViewById(R.id.img_user);
            lv_row.ten = (TextView) convertView.findViewById(R.id.tv_name);
            lv_row.xoa = (CheckBox) convertView.findViewById(R.id.ckb_xoa);

            convertView.setTag(lv_row);
        } else //nếu đã tạo rồi thì ép kiểu lại
            lv_row = (ListView_Row) convertView.getTag();

        //gán dữ liệu từ ArrayList
        if (list.get(position).isGender())
            lv_row.hinh.setImageResource(R.drawable.male_user);
        else
            lv_row.hinh.setImageResource(R.drawable.female_user);

        lv_row.ten.setText(list.get(position).toString());

        return convertView;
    }

    private class ListView_Row {
        public ImageView hinh;
        public TextView ten;
        public CheckBox xoa;
    }
}

