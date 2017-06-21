package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.cihon.androidrestart_keven.R;
import com.nineoldandroids.animation.ValueAnimator;



/**
 * Created by Jack on 2015/10/19.
 */
public class LineScaleIndicator extends BaseIndicatorController {

    public static final float SCALE=1.0f;

    float[] scaleYFloats=new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,};
    private Paint mPaint;
    private ValueAnimator mScaleAnim;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float translateX=getWidth()/11;
        float translateY=getHeight()/2;
        for (int i = 0; i < 5; i++) {
            mPaint = new Paint();
            if(i==1||i==3){
                mPaint.setColor(getTarget().getResources().getColor(R.color.linecolor1));
            }else {
                mPaint.setColor(getTarget().getResources().getColor(R.color.linecolor));
            }
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
            canvas.save();
            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
            canvas.scale(SCALE, scaleYFloats[i]);
            RectF rectF=new RectF(-translateX/2,-getHeight()/2.5f,translateX/2,getHeight()/2.5f);
            canvas.drawRoundRect(rectF, 5, 5, mPaint);
            canvas.restore();
        }
    }

    @Override
    public void createAnimation() {
        long[] delays=new long[]{400,5,200,800,500};
        for (int i = 0; i < 5; i++) {
            final int index=i;

            /*Keyframe k1=Keyframe.ofFloat(0,1);
            k1.setInterpolator(new CubicBezierInterpolator(0.2, 0.68,0.18, 1.08));
            Keyframe k2=Keyframe.ofFloat(0.5f,0.4f);
            k2.setInterpolator(new CubicBezierInterpolator(0.2, 0.68,0.18, 1.08));
            Keyframe k3=Keyframe.ofFloat(1,1);
            PropertyValuesHolder holder=PropertyValuesHolder.ofKeyframe("scale", k1, k2, k3);
            ValueAnimator scaleAnim=ValueAnimator.ofPropertyValuesHolder(holder);
            scaleAnim.setInterpolator(new CubicBezierInterpolator(0.2, 0.68,0.18, 1.08));*/

            mScaleAnim = ValueAnimator.ofFloat(1, 0.14f, 1);
            mScaleAnim.setDuration(1000);
            mScaleAnim.setRepeatCount(-1);
            mScaleAnim.setStartDelay(delays[i]);
            mScaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleYFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mScaleAnim.start();
        }
    }

    @Override
    public void stopAnimation() {
        if(mScaleAnim!=null){
            mScaleAnim.end();
        }
    }

}