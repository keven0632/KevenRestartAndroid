package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallZigZagIndicator extends BaseIndicatorController {

    float[] translateX=new float[2],translateY=new float[2];
    private ValueAnimator mTranslateXAnim;
    private ValueAnimator mTranslateYAnim;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < 2; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, getWidth() / 10, paint);
            canvas.restore();
        }
    }

    @Override
    public void createAnimation() {
        float startX=getWidth()/6;
        float startY=getWidth()/6;
        for (int i = 0; i < 2; i++) {
            final int index=i;
            mTranslateXAnim = ValueAnimator.ofFloat(startX,getWidth()-startX,getWidth()/2,startX);
            if (i==1){
                mTranslateXAnim = ValueAnimator.ofFloat(getWidth()-startX,startX,getWidth()/2,getWidth()-startX);
            }
            mTranslateYAnim = ValueAnimator.ofFloat(startY,startY,getHeight()/2,startY);
            if (i==1){
                mTranslateYAnim = ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,getHeight()/2,getHeight()-startY);
            }

            mTranslateXAnim.setDuration(1000);
            mTranslateXAnim.setInterpolator(new LinearInterpolator());
            mTranslateXAnim.setRepeatCount(-1);
            mTranslateXAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateX [index]= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mTranslateXAnim.start();

            mTranslateYAnim.setDuration(1000);
            mTranslateYAnim.setInterpolator(new LinearInterpolator());
            mTranslateYAnim.setRepeatCount(-1);
            mTranslateYAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateY [index]= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mTranslateYAnim.start();
        }
    }

    @Override
    public void stopAnimation() {
        if(mTranslateXAnim.isRunning()){
            mTranslateXAnim.end();
        }
        if(mTranslateYAnim.isRunning()){
            mTranslateYAnim.end();
        }
    }

}
