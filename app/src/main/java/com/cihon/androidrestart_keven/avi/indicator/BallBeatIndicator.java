package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallBeatIndicator extends BaseIndicatorController {

    public static final float SCALE = 1.0f;

    public static final int ALPHA = 255;

    private float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE};

    int[] alphas = new int[]{ALPHA,
            ALPHA,
            ALPHA,};
    private ValueAnimator mScaleAnim;
    private ValueAnimator mAlphaAnim;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4;
        float radius = (getWidth() - circleSpacing * 2) / 6;
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            paint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restore();
        }
    }

    @Override
    public void createAnimation() {
        int[] delays = new int[]{350, 0, 350};
        for (int i = 0; i < 3; i++) {
            final int index = i;
            mScaleAnim = ValueAnimator.ofFloat(1, 0.75f, 1);
            mScaleAnim.setDuration(700);
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

            mAlphaAnim = ValueAnimator.ofInt(255, 51, 255);
            mAlphaAnim.setDuration(700);
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
