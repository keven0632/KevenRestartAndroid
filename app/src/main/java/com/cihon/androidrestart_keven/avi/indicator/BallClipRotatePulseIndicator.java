package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallClipRotatePulseIndicator extends BaseIndicatorController {

    float scaleFloat1,scaleFloat2,degrees;
    private ValueAnimator mScaleAnim;
    private ValueAnimator mScaleAnim2;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing=12;
        float x=getWidth()/2;
        float y=getHeight()/2;

        //draw fill circle
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat1, scaleFloat1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, x / 2.5f, paint);

        canvas.restore();

        canvas.translate(x, y);
        canvas.scale(scaleFloat2, scaleFloat2);
        canvas.rotate(degrees);

        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        //draw two arc
        float[] startAngles=new float[]{225,45};
        for (int i = 0; i < 2; i++) {
            RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,x-circleSpacing,y-circleSpacing);
            canvas.drawArc(rectF, startAngles[i], 90, false, paint);
        }
    }

    @Override
    public void createAnimation() {
        mScaleAnim = ValueAnimator.ofFloat(1,0.3f,1);
        mScaleAnim.setDuration(1000);
        mScaleAnim.setRepeatCount(-1);
        mScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat1 = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mScaleAnim.start();

        mScaleAnim2 = ValueAnimator.ofFloat(1,0.6f,1);
        mScaleAnim2.setDuration(1000);
        mScaleAnim2.setRepeatCount(-1);
        mScaleAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat2 = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mScaleAnim2.start();

        ValueAnimator rotateAnim= ValueAnimator.ofFloat(0, 180,360);
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        rotateAnim.start();
    }

    @Override
    public void stopAnimation() {
        if(mScaleAnim.isRunning()){
            mScaleAnim.end();
        }
        if(mScaleAnim2.isRunning()){
            mScaleAnim2.end();
        }
    }

}
