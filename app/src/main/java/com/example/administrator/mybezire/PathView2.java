package com.example.administrator.mybezire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class PathView2 extends View {
    private Bitmap bitmap;
    private Paint paint;
    private PathMeasure pathMeasure;
    Path circlePath;
    int width, height;

    private void init() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fly, options);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        circlePath = new Path();
        circlePath.addCircle(0, 0, 200, Path.Direction.CW);
        pathMeasure = new PathMeasure(circlePath, false);
    }

    public PathView2(Context context) {
        super(context);
        init();
    }

    public PathView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

    }

    float pos[] = new float[2];
    float tan[] = new float[2];
    float i;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width / 2, height / 2);
        canvas.drawPath(circlePath, paint);

        i = i + 0.01f;
        if (i > 1) {
            i = 0;
        }
        /*pathMeasure.getPosTan((float) pathMeasure.getLength()*i/100,pos,tan);

        float degree= (float) (Math.atan2(tan[1],tan[0])*180/Math.PI);
        Log.e("xxx","degree: "+degree+"pos[0],pos[1]"+pos[0]+"---"+pos[1]);
        Matrix m=new Matrix();
        m.postRotate(degree);
        m.postTranslate(pos[0]-bitmap.getWidth()/2,pos[1]-bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap,m,null);
        invalidate();*/
        Matrix matrix = new Matrix();
        pathMeasure.getMatrix((float) pathMeasure.getLength() * i, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        matrix.preTranslate(-bitmap.getWidth()/2,-bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, matrix, null);

        invalidate();
    }
}
