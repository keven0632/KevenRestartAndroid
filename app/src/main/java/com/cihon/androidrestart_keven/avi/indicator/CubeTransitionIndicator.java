package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/18.
 */
public class CubeTransitionIndicator extends BaseIndicatorController {

    float[] translateX=new float[2],translateY=new float[2];
    float degrees,scaleFloat=1.0f;
    private ValueAnimator mTranslationXAnim;
    private ValueAnimator mRotateAnim;
    private ValueAnimator mScaleAnim;
    private ValueAnimator mTranslationYAnim;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float rWidth=getWidth()/5;
        float rHeight=getHeight()/5;
        for (int i = 0; i < 2; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.rotate(degrees);
            canvas.scale(scaleFloat,scaleFloat);
            RectF rectF=new RectF(-rWidth/2,-rHeight/2,rWidth/2,rHeight/2);
            canvas.drawRect(rectF,paint);
            canvas.restore();
        }
    }

    @Override
    public void createAnimation() {
        float startX=getWidth()/5;
        float startY=getHeight()/5;
        for (int i = 0; i < 2; i++) {
            final int index=i;
            translateX[index]=startX;
            mTranslationXAnim = ValueAnimator.ofFloat(startX,getWidth()-startX,getWidth()-startX, startX,startX);
            if (i==1){
                mTranslationXAnim = ValueAnimator.ofFloat(getWidth()-startX,startX,startX, getWidth()-startX,getWidth()-startX);
            }
            mTranslationXAnim.setInterpolator(new LinearInterpolator());
            mTranslationXAnim.setDuration(1600);
            mTranslationXAnim.setRepeatCount(-1);
            mTranslationXAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateX[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mTranslationXAnim.start();
            translateY[index]=startY;
            mTranslationYAnim = ValueAnimator.ofFloat(startY,startY,getHeight()-startY,getHeight()- startY,startY);
            if (i==1){
                mTranslationYAnim = ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,startY,startY,getHeight()-startY);
            }
            mTranslationYAnim.setDuration(1600);
            mTranslationYAnim.setInterpolator(new LinearInterpolator());
            mTranslationYAnim.setRepeatCount(-1);
            mTranslationYAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateY[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mTranslationYAnim.start();
        }

        mScaleAnim = ValueAnimator.ofFloat(1,0.5f,1,0.5f,1);
        mScaleAnim.setDuration(1600);
        mScaleAnim.setInterpolator(new LinearInterpolator());
        mScaleAnim.setRepeatCount(-1);
        mScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleFloat = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mScaleAnim.start();

        mRotateAnim = ValueAnimator.ofFloat(0,180,360,1.5f*360,2*360);
        mRotateAnim.setDuration(1600);
        mRotateAnim.setInterpolator(new LinearInterpolator());
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
        if(mTranslationXAnim.isRunning()){
            mTranslationXAnim.end();
        }
        if(mTranslationYAnim.isRunning()){
            mTranslationYAnim.end();
        }
    }
}
