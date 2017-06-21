package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/16.
 */
public class PacmanIndicator extends BaseIndicatorController{

    private float translateX;

    private int alpha;

    private float degrees1,degrees2;
    private ValueAnimator mTranslationAnim;
    private ValueAnimator mAlphaAnim;
    private ValueAnimator mRotateAnim1;
    private ValueAnimator mRotateAnim2;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        drawPacman(canvas,paint);
        drawCircle(canvas,paint);
    }

    private void drawPacman(Canvas canvas, Paint paint){
        float x=getWidth()/2;
        float y=getHeight()/2;

        canvas.save();

        canvas.translate(x, y);
        canvas.rotate(degrees1);
        paint.setAlpha(255);
        RectF rectF1=new RectF(-x/1.7f,-y/1.7f,x/1.7f,y/1.7f);
        canvas.drawArc(rectF1, 0, 270, true, paint);

        canvas.restore();

        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(degrees2);
        paint.setAlpha(255);
        RectF rectF2=new RectF(-x/1.7f,-y/1.7f,x/1.7f,y/1.7f);
        canvas.drawArc(rectF2,90,270,true,paint);
        canvas.restore();
    }


    private void drawCircle(Canvas canvas, Paint paint) {
        float radius=getWidth()/11;
        paint.setAlpha(alpha);
        canvas.drawCircle(translateX, getHeight() / 2, radius, paint);
    }

    @Override
    public void createAnimation() {
        float startT=getWidth()/11;
        mTranslationAnim = ValueAnimator.ofFloat(getWidth()-startT,getWidth()/2);
        mTranslationAnim.setDuration(650);
        mTranslationAnim.setInterpolator(new LinearInterpolator());
        mTranslationAnim.setRepeatCount(-1);
        mTranslationAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                translateX = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mTranslationAnim.start();

        mAlphaAnim = ValueAnimator.ofInt(255,122);
        mAlphaAnim.setDuration(650);
        mAlphaAnim.setRepeatCount(-1);
        mAlphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAlphaAnim.start();

        mRotateAnim1 = ValueAnimator.ofFloat(0, 45, 0);
        mRotateAnim1.setDuration(650);
        mRotateAnim1.setRepeatCount(-1);
        mRotateAnim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees1 = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mRotateAnim1.start();

        mRotateAnim2 = ValueAnimator.ofFloat(0,-45,0);
        mRotateAnim2.setDuration(650);
        mRotateAnim2.setRepeatCount(-1);
        mRotateAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees2 = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mRotateAnim2.start();
    }

    @Override
    public void stopAnimation() {
        if(mTranslationAnim.isRunning()){
            mTranslationAnim.end();
        }
        if(mAlphaAnim.isRunning()){
            mAlphaAnim.end();
        }
        if(mRotateAnim1.isRunning()){
            mRotateAnim1.end();
        }
        if(mRotateAnim2.isRunning()){
            mRotateAnim2.end();
        }
    }
}
