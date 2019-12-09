package com.wind.a1_menu;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.RotateAnimation;

class Tools {
    public static void hideView(View view){
//        RotateAnimation rotateAnimation=new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());
//        rotateAnimation.setDuration(500);
//        rotateAnimation.setFillAfter(true);
//        view.startAnimation(rotateAnimation);
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",0,180);
        animator.setDuration(500);
        animator.start();
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());


    }
    public static void  showView(View view){
//        RotateAnimation rotateAnimation=new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());
//        rotateAnimation.setDuration(500);
//        rotateAnimation.setFillAfter(true);
//        view.startAnimation(rotateAnimation);
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",180,360);
        animator.setDuration(500);
        animator.start();
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }
}
