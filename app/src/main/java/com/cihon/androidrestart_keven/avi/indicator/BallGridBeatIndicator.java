package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Jack on 2015/10/20.
 */
public class BallGridBeatIndicator extends BaseIndicatorController {

    public static final int ALPHA=255;

    int[] alphas=new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};
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
                paint.setAlpha(alphas[3 * i + j]);
                canvas.drawCircle(0, 0, radius, paint);
                canvas.restore();
            }
        }
    }

    @Override
    public void createAnimation() {
        int[] durations={960, 930, 1190, 1130, 1340, 940, 1200, 820, 1190};
        int[] delays= {360, 400, 680, 410, 710, -150, -120, 10, 320};

        for (int i = 0; i < 9; i++) {
            final int index=i;
            mAlphaAnim = ValueAnimator.ofInt(255, 168,255);
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
        if(mAlphaAnim.isRunning()){
            mAlphaAnim.end();
        }

    }


}
