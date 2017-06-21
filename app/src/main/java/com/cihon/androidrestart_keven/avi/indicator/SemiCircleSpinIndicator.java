package com.cihon.androidrestart_keven.avi.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Jack on 2015/10/20.
 */
public class SemiCircleSpinIndicator extends BaseIndicatorController {


    private ObjectAnimator mRotateAnim;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        RectF rectF=new RectF(0,0,getWidth(),getHeight());
        canvas.drawArc(rectF,-60,120,false,paint);
    }

    @Override
    public void createAnimation() {
        mRotateAnim = ObjectAnimator.ofFloat(getTarget(),"rotation",0,180,360);
        mRotateAnim.setDuration(600);
        mRotateAnim.setRepeatCount(-1);
        mRotateAnim.start();
    }

    @Override
    public void stopAnimation() {
        if(mRotateAnim.isRunning()){
            mRotateAnim.end();
        }

    }


}
