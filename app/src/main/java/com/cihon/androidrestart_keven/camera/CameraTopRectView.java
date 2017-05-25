package com.cihon.androidrestart_keven.camera;

/**
 * Created by zhengjian on 2017/5/19.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


class CameraTopRectView extends View {

    private int panelWidth;
    private int panelHeght;

    private int viewWidth;
    private int viewHeight;

    public int rectWidth;
    public int rectHeght;

    private int rectTop;
    private int rectLeft;
    private int rectRight;
    private int rectBottom;

    private int lineLen;
    private int lineWidht;
    private static final int LINE_WIDTH = 5;
    private static final int TOP_BAR_HEIGHT = 50;
    private static final int BOTTOM_BTN_HEIGHT = 66;


    private static final int LEFT_PADDING = 10;
    private static final int RIGHT_PADDING = 10;
    private static final String TIPS = "中华人民共和国机动车行驶证";
    private static final String TIPS_bottom = "将行驶证主页置于此区域，并对齐左下角发证机关印章";

    private Paint linePaint;
    private Paint wordPaint;
    private Paint wordPaint_bottom;
    private Rect rect;
    private int baseline;
    private int baseline1;

    DisplayMetrics displayMetrics;
    public static float RATIO;
    public static int OFFSET_LEFT;
    public static int OFFSET_TOP;
    public static int TEXT_SIZE_TOP=45;
    public static int TEXT_SIZE_Bottom=30;
    private int text_top;
    private int text_bottom;
    private int text30;
    private int text70;
    private int text10;
    private int text80;
    private int text260;
    private int text300;

    public CameraTopRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        Activity activity = (Activity) context;

        /**
         * 计算不同分辨率的大小不同
         */
        //1.获取当前设备的屏幕大小
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //2.计算与你开发时设定的屏幕大小的纵横比(这里假设你开发时定的屏幕大小是480*800)
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        float ratioWidth = (float) screenWidth / 1080;
        float ratioHeight = (float) screenHeight / 1920;
        //1920 1080//800  480
        RATIO = Math.max(ratioWidth, ratioHeight);
        if (ratioWidth != ratioHeight) {
            if (RATIO == ratioWidth) {
                OFFSET_LEFT = 0;
                OFFSET_TOP = Math.round((screenHeight - 1920 * RATIO) / 2);
            } else {
                OFFSET_LEFT = Math.round((screenWidth - 1080 * RATIO) / 2);
                OFFSET_TOP = 0;
            }
        }
        //在自定义view中使用：3.根据上一步计算出来的
        // 最小纵横比来确定字体的大小(假定在480*800屏幕下字体大小设定为35)
         text_top= Math.round(TEXT_SIZE_TOP * RATIO);
         text_bottom= Math.round(TEXT_SIZE_Bottom * RATIO);
         text30= Math.round(30 * RATIO);
         text70= Math.round(70 * RATIO);
         text260= Math.round(260 * RATIO);
         text300= Math.round(300 * RATIO);
         text10= Math.round(10 * RATIO);
         text80= Math.round(80 * RATIO);
        //4.根据上一步计算的字体大小来设定应用程序中字体的大小

        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        panelWidth = wm.getDefaultDisplay().getWidth();//拿到屏幕的宽
        panelHeght = wm.getDefaultDisplay().getHeight();//拿到屏幕的高

        //高度不需要dp转换px,不然整体相机会向上移动一小节
//        viewHeight = panelHeght - (int) DisplayUtil.dp2px(activity,TOP_BAR_HEIGHT + BOTTOM_BTN_HEIGHT);

        viewHeight = panelHeght;
        //viewHeight,界面的高,viewWidth,界面的宽
        viewWidth = panelWidth;
        Log.e("Log","界面宽度--"+viewWidth+"界面高度--"+viewHeight);

        /*rectWidth = panelWidth
                - UnitUtils.getInstance(activity).dip2px(
                        LEFT_PADDING + RIGHT_PADDING);*/

        rectWidth = panelWidth - DisplayUtil.dp2px(activity, LEFT_PADDING + RIGHT_PADDING);
        Log.e("Log","自定义控件宽度--"+rectWidth);
        rectHeght = (int) (rectWidth * 54 / 85.6);
        // 相对于此view
        rectTop = (viewHeight - rectHeght) / 2;
        rectLeft = (viewWidth - rectWidth) / 2;
        rectBottom = rectTop + rectHeght;
        rectRight = rectLeft + rectWidth;

        lineLen = panelWidth / 8;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.rgb(0xdd, 0x42, 0x2f));
        linePaint.setStyle(Style.STROKE);
        linePaint.setStrokeWidth(LINE_WIDTH);// 设置线宽
        linePaint.setAlpha(255);

        wordPaint = new Paint();
        wordPaint.setAntiAlias(true);
        wordPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wordPaint.setStrokeWidth(3);
        wordPaint.setTextSize(text_top);

        wordPaint_bottom = new Paint();
        wordPaint_bottom.setAntiAlias(true);
        wordPaint_bottom = new Paint(Paint.ANTI_ALIAS_FLAG);
        wordPaint_bottom.setStrokeWidth(3);
        wordPaint_bottom.setTextSize(text_bottom);

        rect = new Rect(rectLeft, rectTop + text10, rectRight, rectTop + text80);
        FontMetricsInt fontMetrics = wordPaint.getFontMetricsInt();
        baseline = rect.top + (rect.bottom - rect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        wordPaint.setTextAlign(Paint.Align.CENTER);

        rect = new Rect(rectLeft, rectBottom + text10, rectRight, rectBottom + text80);
        FontMetricsInt fontMetrics1 = wordPaint_bottom.getFontMetricsInt();
        baseline1 = rect.top + (rect.bottom - rect.top - fontMetrics1.bottom + fontMetrics1.top) / 2 - fontMetrics1.top;
        wordPaint_bottom.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        wordPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(rect, wordPaint);

        //画左下角正方形
//        wordPaint.setColor(Color.RED);
        rect = new Rect(rectLeft + text70, rectBottom - text260, rectLeft + text300, rectBottom - text30);
        canvas.drawRect(rect, linePaint);


        //画蒙层
        wordPaint.setColor(0xa0000000);
        rect = new Rect(0, viewHeight / 2 + rectHeght / 2, viewWidth, viewHeight);
        canvas.drawRect(rect, wordPaint);

        rect = new Rect(0, 0, viewWidth, viewHeight / 2 - rectHeght / 2);
        canvas.drawRect(rect, wordPaint);

        rect = new Rect(0, viewHeight / 2 - rectHeght / 2, (viewWidth - rectWidth) / 2, viewHeight / 2 + rectHeght / 2);
        canvas.drawRect(rect, wordPaint);

        rect = new Rect(viewWidth - (viewWidth - rectWidth) / 2, viewHeight / 2 - rectHeght / 2, viewWidth, viewHeight / 2 + rectHeght / 2);
        canvas.drawRect(rect, wordPaint);

        //重制rect  并画文字  吧文字置于rect中间  绘制底部文字
        rect = new Rect(rectLeft, rectTop - text80, rectRight, rectTop - text10);
        wordPaint_bottom.setColor(Color.WHITE);
        canvas.drawText(TIPS_bottom, rect.centerX(), baseline1, wordPaint_bottom);

        //重制rect  并画文字  吧文字置于rect中间
        rect = new Rect(rectLeft, rectTop - text80, rectRight, rectTop - text10);
        wordPaint.setColor(Color.WHITE);
        canvas.drawText(TIPS, rect.centerX(), baseline, wordPaint);
//        canvas.drawLine(rectLeft, rectTop, rectLeft + lineLen, rectTop,
//                linePaint);
//        canvas.drawLine(rectRight - lineLen, rectTop, rectRight, rectTop,
//                linePaint);
//        canvas.drawLine(rectLeft, rectTop, rectLeft, rectTop + lineLen,
//                linePaint);
//        canvas.drawLine(rectRight, rectTop, rectRight, rectTop + lineLen,
//                linePaint);
//        canvas.drawLine(rectLeft, rectBottom, rectLeft + lineLen, rectBottom,
//                linePaint);
//        canvas.drawLine(rectRight - lineLen, rectBottom, rectRight, rectBottom,
//                linePaint);
//        canvas.drawLine(rectLeft, rectBottom - lineLen, rectLeft, rectBottom,
//                linePaint);
//        canvas.drawLine(rectRight, rectBottom - lineLen, rectRight, rectBottom,
//                linePaint);
    }

    public int getRectLeft() {
        return rectLeft;
    }

    public int getRectTop() {
        return rectTop;
    }

    public int getRectRight() {
        return rectRight;
    }

    public int getRectBottom() {
        return rectBottom;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

}
