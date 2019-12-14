package com.wind.a4_switch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyToggleButton extends View implements View.OnClickListener {
    private Bitmap backgroundBitmap;
    private Bitmap slidingBitmap;
    private int slideLeftMax;
    private Paint paint;
    private int slideLeft;
    private float startX;
    private float lastX;
    private boolean isOpen=false;

    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint=new Paint();
        paint.setAntiAlias(true);
        backgroundBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
        slidingBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
        slideLeftMax=backgroundBitmap.getWidth()-slidingBitmap.getWidth();

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backgroundBitmap.getWidth(),backgroundBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap,0,0,paint);
        canvas.drawBitmap(slidingBitmap,slideLeft,0,paint);

    }
    //true点击事件生效，滑动事件不生效；false点击事件不生效，滑动事件生效
   
private boolean isEnableClick=true;
    @Override
    public void onClick(View v) {
//        isOpen=!isOpen;
//        if(isOpen){
//            slideLeft=slideLeftMax;
//        }else {
//            slideLeft=0;
//        }
//        invalidate();
        
        if(isEnableClick){
            isOpen=!isOpen;
            flushView();
        }
    }

    private void flushView() {

        if(isOpen){
            slideLeft=slideLeftMax;
        }else {
            slideLeft=0;
        }
        invalidate();
    }

   public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=startX=event.getX();
                isEnableClick=true;
                break;
            case MotionEvent.ACTION_MOVE:
                float endX=event.getX();
                float distanceX=endX-startX;
                slideLeft+=distanceX;
                if(slideLeft<0){
                    slideLeft=0;
                }else if(slideLeft>slideLeftMax) {
                    slideLeft=slideLeftMax;
                }
                invalidate();
                if(Math.abs(endX-lastX)>5){
                    isEnableClick=false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!isEnableClick){
                    if(slideLeft>slideLeftMax/2){
                        isOpen=true;
                    }else {
                        isOpen=false;
                    }
                    flushView();
                }
                break;
        }
        return  true;
   }
}
