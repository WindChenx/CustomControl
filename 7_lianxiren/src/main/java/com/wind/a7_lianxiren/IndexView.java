package com.wind.a7_lianxiren;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class IndexView extends View {

    private int itemWidth;
    private int itemHeight;

    private Paint paint;


    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public IndexView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth=getMeasuredWidth();
        itemHeight=getMeasuredHeight()/words.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<words.length;i++){
            if(touchIndex==i){
                paint.setColor(Color.GRAY);
            }else{
                paint.setColor(Color.WHITE);
            }
            String word=words[i];

            Rect rect=new Rect();
            paint.getTextBounds(word,0,1,rect);
            int wordWidth=rect.width();
            int wordHeight=rect.height();
            float wordX=itemWidth/2-wordWidth/2;
            float wordY=itemHeight/2+wordHeight/2+i*itemHeight;
            canvas.drawText(word,wordX,wordY,paint);

        }

    }

    private  int touchIndex=-1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float Y=event.getY();
                int index=(int)(Y/itemHeight);
                if(index!=touchIndex){
                    touchIndex=index;
                    invalidate();
                    if(onIndexChangeListener!=null&&touchIndex<words.length){
                        onIndexChangeListener.onIndexChange(words[touchIndex]);
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                touchIndex=-1;
                invalidate();
        }

        return true;
    }

    public  interface OnIndexChangeListener{
        void onIndexChange(String word);
    }
   private OnIndexChangeListener onIndexChangeListener;

    public  void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener){
        this.onIndexChangeListener=onIndexChangeListener;
    }
}
