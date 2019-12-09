package com.wind.a2_slide;

import android.app.Activity;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;
    private  int prePosition=0;
    private boolean isDragging=false;
    private final int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e };

    private final String[] imageDescriptions = {
            "动漫1",
            "动漫2",
            "动漫3",
            "动漫4",
            "动漫5"
    };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item=viewpager.getCurrentItem()+1;
            viewpager.setCurrentItem(item);
            handler.sendEmptyMessageDelayed(0,5000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);

        imageViews=new ArrayList<>();
        for(int i=0;i<imageIds.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);

            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,8),DensityUtil.dip2px(this,8));

            if(i==0){
                point.setEnabled(true);
            }else {
                point.setEnabled(false);
                params.leftMargin=DensityUtil.dip2px(this,8);
            }
            point.setLayoutParams(params);

            ll_point_group.addView(point);
        }
        tv_title.setText(imageDescriptions[prePosition]);
        viewpager.setAdapter(new MypagerAdapter());
        viewpager.addOnPageChangeListener(new MyOnPagerChangeListener());
        int x=800;
        int item=x/2-x/2%imageViews.size();
        viewpager.setCurrentItem(item);
        handler.sendEmptyMessageDelayed(0,8000);
    }
    class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 页面滚动时回调
         *
         * @param i
         * @param v
         * @param i1
         */
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        //页面选中时回调\\\\\\\\\\\\\\\\\\\\\\\\\\
        @Override
        public void onPageSelected(int i) {
            int realPosition = i % imageViews.size();
            tv_title.setText(imageDescriptions[realPosition]);
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            ll_point_group.getChildAt(realPosition).setEnabled(true);
            prePosition = realPosition;

        }

        //页面滚动状态改变时回调
        @Override
        public void onPageScrollStateChanged(int i) {
            if (i == ViewPager.SCROLL_STATE_DRAGGING) {
                isDragging=true;
                handler.removeCallbacksAndMessages(null);

            } else if (i == ViewPager.SCROLL_STATE_SETTLING) {

            } else if (i == ViewPager.SCROLL_STATE_IDLE&&isDragging) {
                isDragging=false;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,5000);

            }
        }
    }

    class MypagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            int x=800;
            return x;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int realPosition=position%imageViews.size();
            ImageView imageView=imageViews.get(realPosition);
            container.addView(imageView);
            imageView.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_UP:
                            handler.sendEmptyMessageDelayed(0,5000);
                    }
                    return true;
                }
            });
            return  imageView;
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }


    }

}
