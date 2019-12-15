package com.wind.a8_slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideLayout extends FrameLayout {
    private View contentView;
    private View menuView;

    private int contentWidth;
    private int menuWidth;
    private int viewHeight;
    private Scroller scroller;

    public SlideLayout( Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView=getChildAt(0);
        menuView=getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentWidth=contentView.getMeasuredWidth();
        menuWidth=menuView.getMeasuredWidth();
        viewHeight=getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //指定删除控件的位置
        menuView.layout(contentWidth,0,contentWidth+menuWidth,viewHeight);

    }
private float startX;
    private  float startY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                startY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX=event.getX();
                float endY=event.getY();
                float distanceX=endX-startX;
                int toScrollx=(int)(getScrollX()-distanceX);
                if(toScrollx<0){
                    toScrollx=0;
                }else  if(toScrollx>menuWidth){
                    toScrollx=menuWidth;
                }
                scrollTo(toScrollx,getScrollY());

                break;
            case MotionEvent.ACTION_UP:
                int totalScrollX = getScrollX();//偏移量
                if(totalScrollX < menuWidth/2){
                    //关闭Menu
                    closeMenu();
                }else{
                    //打开Menu
                    openMenu();
                }
                break;
        }
        return true;
    }
    public void openMenu() {
        //--->menuWidth
        int distanceX = menuWidth - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();//强制刷新

    }

    /**
     * 关闭menu
     */
    public void closeMenu() {
        //--->0
        int distanceX = 0 - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();//强制刷新

    }
}
