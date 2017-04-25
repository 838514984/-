package com.example.administrator.mybezire;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class PathView extends View {
    public PathView(Context context) {
        super(context);
        init();
        initValueAnimation();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initValueAnimation();
    }
    PathMeasure pathMeasure;
    Path searchPath;
    Path showPath;
    Paint paint;
    private void init(){
        searchPath=new Path();                      //已经画好的
        paint=new Paint();
        showPath=new Path();                        //用来赋值显示的
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        RectF rectF=new RectF(-100,-100,100,100);
        searchPath.addArc(rectF,45,359.99f);        // 画圆圈
        searchPath.lineTo(150,150);                 //画把手
        pathMeasure=new PathMeasure(searchPath,false);
        Log.e("xxx",pathMeasure+"");

    }
    ValueAnimator valueAnimator;                    //获得数值用的
    int value;
    private void initValueAnimation(){
        valueAnimator=ValueAnimator.ofInt(100,0);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                showPath.reset();
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value= (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();

    }

    int height,width;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height=h;
        width=w;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width/2,height/2);
        canvas.drawColor(Color.BLUE);
        //起始长度不能大于总长度
        pathMeasure.getSegment((float) (pathMeasure.getLength()*value/100),(float)(pathMeasure.getLength()),showPath,true);
        canvas.drawPath(showPath,paint);

    }
}
