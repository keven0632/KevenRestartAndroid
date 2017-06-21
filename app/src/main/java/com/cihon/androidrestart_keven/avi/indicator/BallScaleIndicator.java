package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallScaleIndicator extends BaseIndicatorController {

    float scale=1;
    int alpha=255;
    private ValueAnimator mScaleAnim;
    private ValueAnimator mAlphaAnim;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing=4;
        paint.setAlpha(alpha);
        canvas.scale(scale,scale,getWidth()/2,getHeight()/2);
        paint.setAlpha(alpha);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-circleSpacing,paint);
    }

    @Override
    public void createAnimation() {
        mScaleAnim = ValueAnimator.ofFloat(0,1);
        mScaleAnim.setInterpolator(new LinearInterpolator());
        mScaleAnim.setDuration(1000);
        mScaleAnim.setRepeatCount(-1);
        mScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mScaleAnim.start();

        mAlphaAnim = ValueAnimator.ofInt(255, 0);
        mAlphaAnim.setInterpolator(new LinearInterpolator());
        mAlphaAnim.setDuration(1000);
        mAlphaAnim.setRepeatCount(-1);
        mAlphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAlphaAnim.start();
    }

    @Override
    public void stopAnimation() {
        if(mScaleAnim.isRunning()){
            mScaleAnim.end();
        }
        if(mAlphaAnim.isRunning()){
            mAlphaAnim.end();
        }
    }


}
