package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

/**
 * Created by Jack on 2015/10/17.
 */
public class BallPulseRiseIndicator extends BaseIndicatorController{

    private PropertyValuesHolder mRotation6;
    private ObjectAnimator mAnimator;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10;
        canvas.drawCircle(getWidth()/4,radius*2,radius,paint);
        canvas.drawCircle(getWidth()*3/4,radius*2,radius,paint);

        canvas.drawCircle(radius,getHeight()-2*radius,radius,paint);
        canvas.drawCircle(getWidth()/2,getHeight()-2*radius,radius,paint);
        canvas.drawCircle(getWidth()-radius,getHeight()-2*radius,radius,paint);
    }

    @Override
    public void createAnimation() {
        mRotation6 = PropertyValuesHolder.ofFloat("rotationX",0,360);
        mAnimator = ObjectAnimator.ofPropertyValuesHolder(getTarget(), mRotation6);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(1500);
        mAnimator.start();
    }

    @Override
    public void stopAnimation() {
        if(mAnimator.isRunning()){
            mAnimator.end();
        }

    }
}
