package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallGridPulseIndicator extends BaseIndicatorController{

    public static final int ALPHA=255;

    public static final float SCALE=1.0f;

    int[] alphas=new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};

    float[] scaleFloats=new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};
    private ValueAnimator mScaleAnim;
    private ValueAnimator mAlphaAnim;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing=4;
        float radius=(getWidth()-circleSpacing*4)/6;
        float x = getWidth()/ 2-(radius*2+circleSpacing);
        float y = getWidth()/ 2-(radius*2+circleSpacing);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.save();
                float translateX=x+(radius*2)*j+circleSpacing*j;
                float translateY=y+(radius*2)*i+circleSpacing*i;
                canvas.translate(translateX, translateY);
                canvas.scale(scaleFloats[3 * i + j], scaleFloats[3 * i + j]);
                paint.setAlpha(alphas[3 * i + j]);
                canvas.drawCircle(0, 0, radius, paint);
                canvas.restore();
            }
        }
    }

    @Override
    public void createAnimation() {
        int[] durations={720, 1020, 1280, 1420, 1450, 1180, 870, 1450, 1060};
        int[] delays= {-60, 250, -170, 480, 310, 30, 460, 780, 450};

        for (int i = 0; i < 9; i++) {
            final int index=i;
            mScaleAnim = ValueAnimator.ofFloat(1,0.5f,1);
            mScaleAnim.setDuration(durations[i]);
            mScaleAnim.setRepeatCount(-1);
            mScaleAnim.setStartDelay(delays[i]);
            mScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mScaleAnim.start();

            mAlphaAnim = ValueAnimator.ofInt(255, 210, 122, 255);
            mAlphaAnim.setDuration(durations[i]);
            mAlphaAnim.setRepeatCount(-1);
            mAlphaAnim.setStartDelay(delays[i]);
            mAlphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphas[index] = (int) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mAlphaAnim.start();
        }
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
