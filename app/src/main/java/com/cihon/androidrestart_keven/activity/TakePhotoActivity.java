package com.cihon.androidrestart_keven.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cihon.androidrestart_keven.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.content.Intent.ACTION_GET_CONTENT;
import static com.cihon.androidrestart_keven.util.Constant.OPEN_ALBUM;
import static com.cihon.androidrestart_keven.util.Constant.REQUEST_CAMERA;
import static com.cihon.androidrestart_keven.util.Constant.REQUEST_STORAGE;
import static com.cihon.androidrestart_keven.util.Constant.TAKE_PHOTO;

public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_camera;
    private Button mBt_album;
    private ImageView mIv_camera;
    private Uri mImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        mBt_camera = (Button) findViewById(R.id.bt_camera);
        mBt_album = (Button) findViewById(R.id.bt_album);
        mIv_camera = (ImageView) findViewById(R.id.iv_camera);

        mBt_camera.setOnClickListener(this);
        mBt_album.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_camera:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(TakePhotoActivity.this, Manifest.permission.CAMERA);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TakePhotoActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                        return;
                    }
                    takePhoto();
                } else {
                    takePhoto();
                }
                break;
            case R.id.bt_album:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(TakePhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TakePhotoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                        return;
                    }
                    openAlbum();
                } else {
                    openAlbum();
                }
                break;
        }
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent(ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_ALBUM);
    }

    /**
     * 调用相机进行拍照
     */
    private void takePhoto() {
    //创建File对象，用于存储拍照后的照片
        File outputImg = new File(getExternalCacheDir(), "output_img.jpg");
        try {
            if (outputImg.exists()) {
                outputImg.delete();
            }
            outputImg.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            mImgUri = FileProvider.getUriForFile(TakePhotoActivity.this, "com.cihon.androidrestart_keven.fileprovider", outputImg);
        } else {
            mImgUri = Uri.fromFile(outputImg);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImgUri);
        startActivityForResult(intent, TAKE_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImgUri));
                        mIv_camera.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case OPEN_ALBUM:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4以及以上系统使用这个方法处理图片
                        handleImgOnKitKat(data);

                    } else {
                        //4.4以下系统使用这个方法处理图片
                        handleImgBeforeKitKat(data);

                    }

                }
                break;
        }
    }

    private void handleImgBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImgOnKitKat(Intent data) {
        String imgPath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imgPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imgPath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri,则使用普通方式处理
            imgPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的uri,直接获取图片路径即可
            imgPath = uri.getPath();
        }
        displayImage(imgPath);

    }

    private void displayImage(String imgPath) {
        if (imgPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            mIv_camera.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "加载图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        //通过uri和selection来获取图片真实的路径
        Cursor cursor = getContentResolver().query(externalContentUri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    Toast.makeText(TakePhotoActivity.this, "您拒绝的权限授予", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(TakePhotoActivity.this, "您拒绝的权限授予", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
