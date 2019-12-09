package com.wind.a1_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    private ImageView icon_home;
    private ImageView icon_menu;
    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;

    private  Boolean isShowlevel1=true;
    private  Boolean isShowlevel2=true;
    private  Boolean isShowlevel3=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon_home = (ImageView) findViewById(R.id.icon_home);
        icon_menu = (ImageView) findViewById(R.id.icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        level3 = (RelativeLayout) findViewById(R.id.level3);
        icon_home.setOnClickListener(new MyOnclickListener());
        icon_menu.setOnClickListener(new MyOnclickListener());
    }
    class MyOnclickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.icon_home:
                    if(isShowlevel2){
                        isShowlevel2=false;
                        Tools.hideView(level2);
                        if(isShowlevel3){
                            isShowlevel3=false;
                            Tools.hideView(level3);
                        }


                    }else {
                        isShowlevel2=true;
                        Tools.showView(level2);
                        isShowlevel3=true;
                        Tools.showView(level3);
                    }
                break;
                case  R.id.icon_menu:
                    if(isShowlevel3){
                        isShowlevel3=false;
                        Tools.hideView(level3);
                    }else {
                        isShowlevel3=true;
                        Tools.showView(level3);
                    }
            }

        }
    }
}
