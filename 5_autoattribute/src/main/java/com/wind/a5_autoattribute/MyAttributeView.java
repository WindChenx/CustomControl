package com.wind.a5_autoattribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class MyAttributeView extends View {
    private  int age;
    private String name;
    private Bitmap bg;
    public MyAttributeView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        //获取属性有三种方式
        //1.用命名空间获取
        //2.遍历属性集合
        //3.使用系统工具，获取属性
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyAttributeView);
        for(int i=0;i<typedArray.getIndexCount();i++){
           int index= typedArray.getIndex(i);
            switch (index){
                case R.styleable.MyAttributeView_my_age:
                    age=typedArray.getInt(index,0);
                    break;
                case R.styleable.MyAttributeView_my_name:
                    name=typedArray.getString(index);
                    break;
                case R.styleable.MyAttributeView_my_bg:
                    Drawable drawable=typedArray.getDrawable(index);
                    BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
                    bg=bitmapDrawable.getBitmap();
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint=new Paint();
        canvas.drawText(name+"---"+age,5,15,paint);
        canvas.drawBitmap(bg,15,30,paint);
    }
}
