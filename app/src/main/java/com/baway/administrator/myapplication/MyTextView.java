package com.baway.administrator.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import static android.graphics.Paint.*;

/**
 * Created by Administrator on 2017/3/9.
 */

public class MyTextView extends View {


    private Paint paint;
    private float dimension,mfloat;
    private Point mPoint,//轴点儿
             //三角形的三个点
            lPoint,rPoint,tPoint;
    private Paint mPaint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.MyTextView);
        //得到自定义属性设置半径
        dimension = (int) array.getDimension(R.styleable.MyTextView_radiue, 100);
        //小圆半径
        mfloat = dimension / 2;

        mPaint = new Paint();
        mPaint.setStyle(Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //得到测量的宽的总值()
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //判断宽的值如果小于半径的两倍
        if(widthSize<2*dimension){
            //就把两倍的半径值赋给宽
            widthSize = (int) (2*dimension);
        }
        int width = MeasureSpec.makeMeasureSpec((int) widthSize, heightMeasureSpec);

        mPoint = new Point(widthSize/2,widthSize/2);
        //3的开根号除以2
        double v = Math.sqrt(3) / 2;
        int r3 = (int) (v * dimension);
        lPoint = new Point(mPoint.x-r3, (int) (mPoint.y+dimension/2));
        rPoint = new Point(mPoint.x+r3, (int) (mPoint.y+dimension/2));
        tPoint = new Point(mPoint.x, (int) (mPoint.y-dimension));


        super.onMeasure(width, width);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制大圆
        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        canvas.drawCircle(mPoint.x,mPoint.y,dimension,p1);

        //大圆内绘制三角形
        Paint p2 = new Paint();
        p2.setColor(Color.LTGRAY);
        p2.setStrokeWidth(20);
        canvas.drawLine(lPoint.x,lPoint.y,rPoint.x,rPoint.y,p2);
        canvas.drawLine(rPoint.x,rPoint.y,tPoint.x,tPoint.y,p2);
        canvas.drawLine(tPoint.x,tPoint.y,lPoint.x,lPoint.y,p2);


        //绘制三角形里面的小圆
        Paint p3 = new Paint();
        p3.setColor(Color.BLUE);
        canvas.drawCircle(mPoint.x,mPoint.y,mfloat,p3);


        //绘制小圆里面的正方形
        Paint p4 = new Paint();
        p4.setColor(Color.CYAN);
        float t1= getWidth()/2-(float)(Math.sqrt(2)/2*(dimension/2));
        float t2= getWidth()/2-(float) (Math.sqrt(2)/8*dimension);
        RectF rectF = new RectF(t1,t1,t2+mfloat,t2+mfloat); //在绘制方块
        canvas.drawRect(rectF,p4);//自绘制矩形


    }
}
