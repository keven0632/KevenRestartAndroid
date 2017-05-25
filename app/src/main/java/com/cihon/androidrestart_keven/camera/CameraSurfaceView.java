package com.cihon.androidrestart_keven.camera;

/**
 * Created by zhengjian on 2017/5/19.//自定义相机
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.cihon.androidrestart_keven.WebViewActivity;
import com.cihon.androidrestart_keven.util.Base64Util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.AutoFocusCallback {

    public interface TransferPhoto {
        void imgBase64(String str,Bitmap bitmap);
    }
    private static final String TAG = "Log";

    private Context mContext;
    private SurfaceHolder holder;
    private Camera mCamera;

    private int mScreenWidth;
    private int mScreenHeight;
    private CameraTopRectView topView;

    public CameraSurfaceView(Context context) {
        this(context, null);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getScreenMetrix(context);
        topView = new CameraTopRectView(context, attrs);

        initView();


    }

    //拿到手机屏幕大小
    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;

    }

    private void initView() {
        holder = getHolder();//获得surfaceHolder引用
        holder.addCallback(this);
//        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置类型

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");
        if (mCamera == null) {
            mCamera = Camera.open();//开启相机
            try {
                mCamera.setPreviewDisplay(holder);//摄像头画面显示在Surface上
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged");

        setCameraParams(mCamera, mScreenWidth, mScreenHeight);
        mCamera.startPreview();
//        mCamera.takePicture(null, null, jpeg);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed");
        mCamera.stopPreview();//停止预览
        mCamera.release();//释放相机资源
        mCamera = null;
        holder = null;
    }

    @Override
    public void onAutoFocus(boolean success, Camera Camera) {
        if (success) {
            Log.e(TAG, "onAutoFocus success=" + success);
            System.out.println(success);
        }
    }


    private void setCameraParams(Camera camera, int width, int height) {
        Log.e(TAG, "setCameraParams  width=" + width + "  height=" + height);
        Camera.Parameters parameters = mCamera.getParameters();
        // 获取摄像头支持的PictureSize列表
        /**
         * 照片尺寸
         */
        Camera.Size preSize = getCloselyPreSize(true, mScreenWidth, mScreenHeight, parameters.getSupportedPictureSizes());
        parameters.setPictureSize(preSize.width, preSize.height);
        Log.e("Log", "宽--" + preSize.width + "高--" + preSize.height);

//        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
//        for (Camera.Size size : pictureSizeList) {
//            Log.e(TAG, "pictureSizeList size.width=" + size.width + "  size.height=" + size.height);
//        }
//        /**从列表中选取合适的分辨率*/
//        Camera.Size picSize = getProperSize(pictureSizeList, ((float) height / width));
//        if (null == picSize) {
//            Log.e(TAG, "null == picSize");
//            picSize = parameters.getPictureSize();
//        }
//        Log.e(TAG, "picSize.width=" + picSize.width + "  picSize.height=" + picSize.height);
//        // 根据选出的PictureSize重新设置SurfaceView大小
//        float w = picSize.width;
//        float h = picSize.height;
//        parameters.setPictureSize(picSize.width, picSize.height);
//        this.setLayoutParams(new FrameLayout.LayoutParams((int) (height * (h / w)), height));

        // 获取摄像头支持的PreviewSize列表
        /**
         * 预览
         */
        preSize = getCloselyPreSize(true, mScreenWidth, mScreenHeight, parameters.getSupportedPreviewSizes());
        parameters.setPreviewSize(preSize.width, preSize.height);

//        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
//
//        for (Camera.Size size : previewSizeList) {
//            Log.e(TAG, "previewSizeList size.width=" + size.width + "  size.height=" + size.height);
//        }
//        Camera.Size preSize = getProperSize(previewSizeList, ((float) height) / width);
        if (null != preSize) {
            Log.e(TAG, "preSize.width=" + preSize.width + "  preSize.height=" + preSize.height);
            parameters.setPreviewSize(preSize.width, preSize.height);
        }

        parameters.setJpegQuality(100); // 设置照片质量
        if (parameters.getSupportedFocusModes().contains(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
        }

        mCamera.cancelAutoFocus();//自动对焦。
        mCamera.setDisplayOrientation(90);// 设置PreviewDisplay的方向，效果就是将捕获的画面旋转多少度显示
        mCamera.setParameters(parameters);

    }

    /**
     * 从列表中选取合适的分辨率
     * 默认w:h = 4:3
     * <p>注意：这里的w对应屏幕的height
     * h对应屏幕的width<p/>
     */
    private Camera.Size getProperSize(List<Camera.Size> pictureSizeList, float screenRatio) {
        Log.e(TAG, "screenRatio=" + screenRatio);
        Camera.Size result = null;
        for (Camera.Size size : pictureSizeList) {
            float currentRatio = ((float) size.width) / size.height;
            if (currentRatio - screenRatio == 0) {
                result = size;
                break;
            }
        }

        if (null == result) {
            for (Camera.Size size : pictureSizeList) {
                float curRatio = ((float) size.width) / size.height;
                if (curRatio == 4f / 3) {// 默认w:h = 4:3
                    result = size;
                    break;
                }
            }
        }

        return result;
    }


    // 拍照瞬间调用
    private Camera.ShutterCallback shutter = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            Log.e(TAG, "shutter");
            System.out.println("执行了吗+1");
        }
    };

    // 获得没有压缩过的图片数据
    private Camera.PictureCallback raw = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera Camera) {
            Log.e(TAG, "raw");
            System.out.println("执行了吗+2");
        }
    };

    //创建jpeg图片回调数据对象
    private Camera.PictureCallback jpeg = new Camera.PictureCallback() {

        private Bitmap bitmap;

        @Override
        public void onPictureTaken(byte[] data, Camera Camera) {


            topView.draw(new Canvas());

            BufferedOutputStream bos = null;
            Bitmap bm = null;
            if (data != null) {


            }

            try {
                // 获得图片
                bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                String str = takePicRootDir(mContext);
                Log.e("Log", "获取当前的存储路径" + str);
                Log.e("Log", "获得了图片");
                Log.e("Log", "内存卡安装" + Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    String filePath = "/sdcard/dyk" + System.currentTimeMillis() + ".JPEG";//照片保存路径
                String filePath = str + System.currentTimeMillis() + ".JPEG";//照片保存路径

//                    //图片存储前旋转
                Matrix m = new Matrix();
                int height = bm.getHeight();
                int width = bm.getWidth();
                m.setRotate(90);
                //旋转后的图片
                bitmap = Bitmap.createBitmap(bm, 0, 0, width, height, m, true);


                System.out.println("执行了吗+3");
                Log.e("Log", "执行了吗+3333333");
                File file = new File(filePath);
                Log.e("Log", "文件地址" + filePath);

                if (!file.exists()) {
                    file.createNewFile();
                }
                bos = new BufferedOutputStream(new FileOutputStream(file));

//                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
//                            data.length);
                Log.e("Log", "压缩前--自定义view的宽--" + topView.getViewWidth() + "自定义view的高--" + topView.getViewHeight());
                Bitmap sizeBitmap = Bitmap.createScaledBitmap(bitmap,
                        topView.getViewWidth(), topView.getViewHeight(), true);
                bm = Bitmap.createBitmap(sizeBitmap, topView.getRectLeft(),
                        topView.getRectTop(),
                        topView.getRectRight() - topView.getRectLeft(),
                        topView.getRectBottom() - topView.getRectTop());// 截取

                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流中

                /**
                 *压缩成base64
                 */
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                String str_byte64 = Base64Util.encode(baos.toByteArray());
                Log.e("Log","压缩成base64---"+str_byte64);

                CameraActivity ac= (CameraActivity) mContext;
                ac.finish();

                WebViewActivity activity=WebViewActivity.instance;
                activity.imgBase64(str_byte64,bm);


//
//                    final BufferedOutputStream finalBos = bos;
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //获取base64
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
//                            int options = 80;
//                            while (baos.toByteArray().length / 1024 > 2048) {
//                                baos.reset();
//                                options -= 10;
//                                if (options <= 0) {
//                                    options = 3;
//                                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//                                    break;
//                                }
//                                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//                            }
//                            String str_byte64 = Base64Util.encode(baos.toByteArray());
//                            Log.e("Log", "执行了吗+44444");
//                            Log.e("Log", "大小=="+baos.toByteArray().length);
//                            Log.e("Log", "base64编码后的图片---" + str_byte64);
//                        }
//                    }).run();

//                } else {
//                    Toast.makeText(mContext, "没有检测到内存卡", Toast.LENGTH_SHORT).show();
//                }
            } catch (Exception e) {
                Log.e("Log", "捕捉到了错误" + e.toString());

                e.printStackTrace();

            } finally {
                try {
                    if (bos != null) {
                        bos.flush();//输出
                        bos.close();//关闭
//                        bm.recycle();// 回收bitmap空间
                        mCamera.stopPreview();// 关闭预览
                        mCamera.startPreview();// 开启预览
                        Log.e("Log", "结束了11111");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public void takePicture() {
        //设置参数,并拍照
        setCameraParams(mCamera, mScreenWidth, mScreenHeight);
        // 当调用camera.takePiture方法后，camera关闭了预览，这时需要调用startPreview()来重新开启预览
        mCamera.takePicture(null, null, jpeg);
    }

//    public void setAutoFocus(){
//        mCamera.autoFocus(this);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    /**
     * 通过对比得到与宽高比最接近的预览尺寸（如果有相同尺寸，优先选择）
     *
     * @param isPortrait    是否竖屏
     * @param surfaceWidth  需要被进行对比的原宽
     * @param surfaceHeight 需要被进行对比的原高
     * @param preSizeList   需要对比的预览尺寸列表
     * @return 得到与原宽高比例最接近的尺寸
     */
    public static Camera.Size getCloselyPreSize(boolean isPortrait, int surfaceWidth, int surfaceHeight, List<Camera.Size> preSizeList) {
        int reqTmpWidth;
        int reqTmpHeight;
        // 当屏幕为垂直的时候需要把宽高值进行调换，保证宽大于高
        if (isPortrait) {
            reqTmpWidth = surfaceHeight;
            reqTmpHeight = surfaceWidth;
        } else {
            reqTmpWidth = surfaceWidth;
            reqTmpHeight = surfaceHeight;
        }
        //先查找preview中是否存在与surfaceview相同宽高的尺寸
        for (Camera.Size size : preSizeList) {
            if ((size.width == reqTmpWidth) && (size.height == reqTmpHeight)) {
                return size;
            }
        }

        // 得到与传入的宽高比最接近的size
        float reqRatio = ((float) reqTmpWidth) / reqTmpHeight;
        float curRatio, deltaRatio;
        float deltaRatioMin = Float.MAX_VALUE;
        Camera.Size retSize = null;
        for (Camera.Size size : preSizeList) {
            curRatio = ((float) size.width) / size.height;
            deltaRatio = Math.abs(reqRatio - curRatio);
            if (deltaRatio < deltaRatioMin) {
                deltaRatioMin = deltaRatio;
                retSize = size;
            }
        }

        return retSize;
    }


    /**
     * 判断当前存储卡是否可用
     **/
    public boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取当前需要查询的文件夹
     **/
    public String takePicRootDir(Context context) {
        if (checkSDCardAvailable()) {
            return Environment.getExternalStorageDirectory() + File.separator;
        } else {
            return context.getFilesDir().getAbsolutePath() + File.separator;
        }
    }


}


