package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/17.
 */
public class BallClipRotateMultipleIndicator extends BaseIndicatorController{

    float scaleFloat=1,degrees;
    private ValueAnimator mScaleAnim;
    private ValueAnimator mRotateAnim;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        float circleSpacing=12;
        float x=getWidth()/2;
        float y=getHeight()/2;

        canvas.save();

        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);

        //draw two big arc
        float[] bStartAngles=new float[]{135,-45};
        for (int i = 0; i < 2; i++) {
            RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,x-circleSpacing,y-circleSpacing);
            canvas.drawArc(rectF, bStartAngles[i], 90, false, paint);
        }

        canvas.restore();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(-degrees);
        //draw two small arc
        float[] sStartAngles=new float[]{225,45};
        for (int i = 0; i < 2; i++) {
            RectF rectF=new RectF(-x/1.8f+circleSpacing,-y/1.8f+circleSpacing,x/1.8f-circleSpacing,y/1.8f-circleSpacing);
            canvas.drawArc(rectF, sStartAngles[i], 90, false, paint);
        }
    }

    @Override
    public void createAnimation() {
        mScaleAnim = ValueAnimator.ofFloat(1,0.6f,1);
        mScaleAnim.setDuration(1000);
        mScaleAnim.setRepeatCount(-1);
        mScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mScaleAnim.start();

        mRotateAnim = ValueAnimator.ofFloat(0, 180,360);
        mRotateAnim.setDuration(1000);
        mRotateAnim.setRepeatCount(-1);
        mRotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mRotateAnim.start();
    }

    @Override
    public void stopAnimation() {
        if(mScaleAnim.isRunning()){
            mScaleAnim.end();
        }
        if(mRotateAnim.isRunning()){
            mRotateAnim.end();
        }
    }

}
