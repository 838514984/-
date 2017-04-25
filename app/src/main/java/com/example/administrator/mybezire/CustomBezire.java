package com.example.administrator.mybezire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class CustomBezire extends View {
    int pointX,pointY;
    int gudingX1=300,gudingY1=300,gudingX2=900,gudingY2=300;
    Path path=new Path();
    public CustomBezire(Context context) {
        super(context);
    }

    public CustomBezire(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }*/
        pointX= (int) event.getX();
        pointY= (int) event.getY();
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(gudingX1,gudingY1,30,paint);
        canvas.drawCircle(gudingX2,gudingY2,30,paint);
        canvas.drawCircle(pointX,pointY,30,paint);
        paint.setColor(Color.GRAY);
        canvas.drawLine(gudingX1,gudingY1,pointX,pointY,paint);
        canvas.drawLine(gudingX2,gudingY2,pointX,pointY,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
        path.moveTo(gudingX1,gudingY1);
        path.quadTo(pointX,pointY,gudingX2,gudingY2);
        canvas.drawPath(path,paint);

    }
}
