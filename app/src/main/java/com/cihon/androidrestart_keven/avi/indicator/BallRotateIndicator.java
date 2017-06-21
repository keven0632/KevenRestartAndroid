package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/17.
 */
public class BallRotateIndicator extends BaseIndicatorController{

    float scaleFloat=0.5f;
    private ValueAnimator mScaleAnim;
    private ObjectAnimator mRotateAnim;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10;
        float x = getWidth()/ 2;
        float y=getHeight()/2;

        canvas.save();
        canvas.translate(x - radius * 2 - radius, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(x + radius * 2 + radius, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.drawCircle(0,0,radius, paint);
        canvas.restore();
    }

    @Override
    public void createAnimation() {
        mScaleAnim = ValueAnimator.ofFloat(0.5f,1,0.5f);
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

        mRotateAnim = ObjectAnimator.ofFloat(getTarget(),"rotation",0,180,360);
        mRotateAnim.setDuration(1000);
        mRotateAnim.setRepeatCount(-1);
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
