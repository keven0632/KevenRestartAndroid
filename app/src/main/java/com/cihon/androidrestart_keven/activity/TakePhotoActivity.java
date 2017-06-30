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
     * �����
     */
    private void openAlbum() {
        Intent intent = new Intent(ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_ALBUM);
    }

    /**
     * ���������������
     */
    private void takePhoto() {
    //����File�������ڴ洢���պ����Ƭ
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
                    //�ж��ֻ�ϵͳ�汾��
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4�Լ�����ϵͳʹ�������������ͼƬ
                        handleImgOnKitKat(data);

                    } else {
                        //4.4����ϵͳʹ�������������ͼƬ
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
            //�����document���͵�uri,��ͨ��document id����
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//���������ָ�ʽ��id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imgPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imgPath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //�����content���͵�uri,��ʹ����ͨ��ʽ����
            imgPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //�����file���͵�uri,ֱ�ӻ�ȡͼƬ·������
            imgPath = uri.getPath();
        }
        displayImage(imgPath);

    }

    private void displayImage(String imgPath) {
        if (imgPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            mIv_camera.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "����ͼƬʧ��", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        //ͨ��uri��selection����ȡͼƬ��ʵ��·��
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
                    Toast.makeText(TakePhotoActivity.this, "���ܾ���Ȩ������", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(TakePhotoActivity.this, "���ܾ���Ȩ������", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
