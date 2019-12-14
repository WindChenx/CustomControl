package com.wind.a6_autoviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPager extends ViewGroup {
    /**
     * 手势识别器的使用
     * 1，定义
     * 2，实例化方法
     * 3，在 onTouchEvent()中把事件传递给手势识别器
     * @param context
     * @param attrs
     */
    private GestureDetector gestureDetector;
    private int currentIndex;//当前页面下标
    private MyScroller scroller;
    public MyViewPager(Context context, AttributeSet attrs) {
//        super(context, attrs);
        super(context, attrs,0);


        initView(context);
    }

    private void initView(Context context) {
        scroller = new MyScroller();
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
               scrollBy((int)distanceX,0);
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i=0;i<getChildCount();i++){
            View childView=getChildAt(i);
            childView.layout(i*getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }
    private float startX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       super.onTouchEvent(event);
       gestureDetector.onTouchEvent(event);
       switch (event.getAction()){
           case MotionEvent.ACTION_DOWN:
               startX=event.getX();
               break;
           case MotionEvent.ACTION_MOVE:
               break;
           case MotionEvent.ACTION_UP:
               float endX=event.getX();
               float distance=endX-startX;
               int tempIndex=currentIndex;
               if((startX-endX)>getWidth()/2){
                   tempIndex++;
               }else if((endX-startX)>getWidth()/2){
                   tempIndex--;
               }
               scrollToPager(tempIndex);
               break;
               
       }
       return  true;
    }
//屏蔽非法值
    private void scrollToPager(int tempIndex) {
        if(tempIndex<0){
            tempIndex=0;
        }
        if(tempIndex>getChildCount()-1){
            tempIndex=getChildCount()-1;
        }
        currentIndex=tempIndex;
        int distanceX = currentIndex*getWidth() - getScrollX();
//        scrollTo(currentIndex*getWidth(),0);
        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if( scroller.cuputeScrollOffset()){

            float currX = scroller.getCurrX();

            scrollTo((int) currX,0);

            invalidate();
        }
    }
}
