package com.cihon.androidrestart_keven.Base;

import com.facebook.stetho.Stetho;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;

import org.litepal.LitePalApplication;

/**
 * Created by zhengjian on 2017/6/1.
 */

public class MyApp extends LitePalApplication {
    public static MyApp application;
    public static ImageLoader mImageLoader = ImageLoader.getInstance();
    String appVersion = "";
    String appId = "";

    public static MyApp getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
        initHotFix();

        //Facebook推出的Stetho
        Stetho.initializeWithDefaults(this);

        application = this;

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheExtraOptions(768, 1280)
                .memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 * 1024))
                .memoryCacheSize(5 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        // 初始化ImageLoader的与配置。

        mImageLoader.init(config);

        ImageLoader.getInstance().init(config);

    }

    private void initApp() {
        this.appId = "82875-1"; //替换掉自己应用的appId
        try {
            this.appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            this.appVersion = "1.0.0";
        }
    }

    private void initHotFix() {
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub((mode, code, info, handlePatchVersion) -> {
                    // 补丁加载回调通知
                    if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                        // 表明补丁加载成功
                    } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                        // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                        // 建议: 用户可以监听进入后台事件, 然后应用自杀
                    } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                        // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                        // SophixManager.getInstance().cleanPatches();
                    } else {
                        // 其它错误信息, 查看PatchStatus类说明
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
