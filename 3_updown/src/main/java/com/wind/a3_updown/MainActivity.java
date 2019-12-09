package com.wind.a3_updown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText et_input;
    private ImageView iv_down_arrow;
    private PopupWindow popupWindow;
    private ListView listView;
    private ArrayList<String> msgs;
    private  MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_input=findViewById(R.id.et_input);
        iv_down_arrow=findViewById(R.id.iv_down_arrow);
        iv_down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow==null){
                    popupWindow=new PopupWindow(MainActivity.this);
                    popupWindow.setWidth(et_input.getWidth());
                   int hight=DensityUtil.dip2px(MainActivity.this,200);
                   popupWindow.setHeight(hight);
                    popupWindow.setContentView(listView);
                    popupWindow.setFocusable(true);
                }
                popupWindow.showAsDropDown(et_input,0,0);
                
            }
        });
        listView=new ListView(this);
        msgs=new ArrayList<>();
        for(int i=0;i<50;i++){
            msgs.add(i+"--aaaaaaaaa--"+i);
        }

        myAdapter=new MyAdapter();

        listView.setAdapter(myAdapter);
        listView.setBackgroundResource(R.drawable.listview_background);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg=msgs.get(position);
                et_input.setText(msg);
                if(popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }
            }
        });
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return msgs.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
           if(convertView==null){
               convertView=View.inflate(MainActivity.this,R.layout.item_main,null);
               viewHolder=new ViewHolder();
               viewHolder.tv_msg=convertView.findViewById(R.id.tv_msg);
               viewHolder.iv_delete=convertView.findViewById(R.id.iv_delete);
               convertView.setTag(viewHolder);
           }else {
               viewHolder= (ViewHolder) convertView.getTag();
           }
           final String msg=msgs.get(position);
           viewHolder.tv_msg.setText(msg);
           viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   msgs.remove(msg);
                   myAdapter.notifyDataSetChanged();

               }
           });
            return convertView;
        }
    }
    static class ViewHolder{
        TextView tv_msg;
        ImageView iv_delete;
    }
}
