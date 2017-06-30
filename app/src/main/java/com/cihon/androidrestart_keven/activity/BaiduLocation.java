package com.cihon.androidrestart_keven.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.util.Constant;

import java.util.List;

import static com.cihon.androidrestart_keven.util.Constant.REQUEST_LOCATION;

public class BaiduLocation extends AppCompatActivity implements BDLocationListener {

    /**
     * {"response":{"gpsItem":["114.34229|23.048237","114.34229|23.048237","114.34229|23.048237","114.34229|23.048235","114.34229|23.048235","114.34229|23.048235","114.34229|23.048235","114.34229|23.048235","114.34229|23.048235","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048233","114.34229|23.048231","114.34229|23.048231","114.34229|23.048231","114.34229|23.04823","114.34229|23.04823","114.34229|23.04823","114.34229|23.04823","114.34229|23.04823","114.34229|23.04823","114.34229|23.048227","114.34229|23.048227","114.34229|23.048227","114.34229|23.048227","114.34229|23.048227","114.34229|23.048227","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048225","114.34229|23.048223","114.34229|23.048223","114.34229|23.048223","114.34229|23.048223","114.34229|23.048223","114.34229|23.048223","114.34229|23.048222","114.34229|23.048222","114.34229|23.048222","114.34229|23.048218","114.34229|23.048218","114.34229|23.048218","114.34229|23.04822","114.34229|23.04822","114.34229|23.04822","114.342285|23.048222","114.342285|23.048222","114.342285|23.048222","114.34227|23.048222","114.34227|23.048222","114.34227|23.048222","114.342255|23.04821","114.342255|23.04821","114.342255|23.04821","114.34226|23.048203","114.34226|23.048203","114.34226|23.048203","114.34226|23.048206","114.34226|23.048206","114.34226|23.048206","114.342255|23.048191","114.342255|23.048191","114.342255|23.048191","114.34223|23.048187","114.34223|23.048187","114.34223|23.048187","114.34217|23.048187","114.34217|23.048187","114.34217|23.048187","114.34206|23.04818","114.34206|23.04818","114.34206|23.04818","114.341896|23.04819","114.341896|23.04819","114.341896|23.04819","114.34173|23.048178","114.34173|23.048178","114.34173|23.048178","114.341576|23.048176","114.341576|23.048176","114.341576|23.048176","114.34146|23.048176","114.34146|23.048176","114.34146|23.048176","114.34132|23.048172","114.34132|23.048172","114.34132|23.048172","114.34126|23.048168","114.34126|23.048168","114.34126|23.048168","114.34124|23.048044","114.34124|23.048044","114.34124|23.048044","114.34125|23.047855","114.34125|23.047855","114.34125|23.047855","114.34125|23.047693","114.34125|23.047693","114.34125|23.047693","114.34123|23.047602","114.34123|23.047602","114.34123|23.047602","114.341125|23.047565","114.341125|23.047565","114.341125|23.047565","114.34101|23.047573","114.34101|23.047573","114.34101|23.047573","114.340965|23.04752","114.340965|23.04752","114.340965|23.04752","114.340965|23.047483","114.340965|23.047483","114.340965|23.047483","114.340965|23.047482","114.340965|23.047482","114.340965|23.047482","114.34097|23.04749","114.34097|23.04749","114.34097|23.04749","114.341|23.0475","114.341|23.0475","114.341|23.0475","114.34104|23.04751","114.34104|23.04751","114.34104|23.04751","114.34108|23.047504","114.34108|23.047504","114.34108|23.047504","114.3411|23.047516","114.3411|23.047516","114.3411|23.047516","114.34107|23.047518","114.34107|23.047518","114.34107|23.047518","114.34094|23.047476","114.34094|23.047476","114.34094|23.047476","114.34093|23.047354","114.34093|23.047354","114.34093|23.047354","114.34098|23.047165","114.34098|23.047165","114.34098|23.047165","114.34106|23.046934","114.34106|23.046934","114.34106|23.046934","114.34115|23.046637","114.34115|23.046637","114.34115|23.046637","114.341255|23.046343","114.341255|23.046343","114.341255|23.046343","114.341415|23.046114","114.341415|23.046114","114.341415|23.046114","114.341644|23.046003","114.341644|23.046003","114.341644|23.046003","114.34191|23.04597","114.34191|23.04597","114.34191|23.04597"]}}

     */

    private TextView mTv;
    private MapView mMapView;
    private LocationClient locationClient;
    private Context context;
    private double mLocation_longt;
    private double mLocation_lat;
    private TextView mTv_jing;
    private LatLng curr_location;
    private GeoCoder mSearch;
    private Marker mPoints;
    private BaiduMap baiduMap;
    private EditText mEt_search;
    private Button mBt_search;
    private PoiSearch mPoiSearch;
    private List<PoiInfo> allPoi;
    private String mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
        //ע��÷���Ҫ��setContentView����֮ǰʵ��
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_baidu_location);

        mTv = (TextView) findViewById(R.id.tv_location);
        mTv_jing = (TextView) findViewById(R.id.tv_jing);
        mMapView = (MapView) findViewById(R.id.mapView);
        mEt_search = (EditText) findViewById(R.id.et_search);
        mBt_search = (Button) findViewById(R.id.bt_search);
        mBt_search.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEt_search.getWindowToken(), 0);
            String str = mEt_search.getText().toString();
            poiSearch(curr_location, str);
        });

        baiduMap = mMapView.getMap();

        context = this;

        addLocalPermission();

    }

    /**
     * poi��������
     *
     * @param latLng  ���ĵ��λ��
     * @param keyword �����ؼ���
     */
    private void poiSearch(final LatLng latLng, final String keyword) {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {

                baiduMap.clear();
                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {

                    Toast.makeText(getApplicationContext(), "δ���������", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {

                    allPoi = poiResult.getAllPoi();

                    initOverlay(allPoi);
                    return;
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
        mPoiSearch.searchNearby((new PoiNearbySearchOption())
                .location(latLng)
                .keyword(keyword)
                .pageNum(0).pageCapacity(10).radius(2000));

    }

    private void initOverlay(final List<PoiInfo> poiInfos) {
        MarkerOptions centerMark = new MarkerOptions().position(curr_location).icon(BitmapDescriptorFactory
                .fromResource(R.mipmap.startmark));
        baiduMap.addOverlay(centerMark);

        for (int i = 0; i < poiInfos.size(); i++) {
            PoiInfo poiInfo = poiInfos.get(i);
            MarkerOptions poiMark = new MarkerOptions().position(poiInfo.location).icon(BitmapDescriptorFactory.fromResource(R.mipmap.endmark))
                    .title(poiInfo.address);
            baiduMap.addOverlay(poiMark);
        }
        baiduMap.setOnMarkerClickListener(marker -> {
            Log.e("tag","�����marker");
            mTv_jing.setText(marker.getTitle());

//            navigation(marker.getPosition().latitude, marker.getPosition().longitude, 18, marker.getTitle());

            RouteParaOption para = new RouteParaOption()
                    .startPoint(curr_location)
                    .endPoint(marker.getPosition())
                    .cityName(mCity);
            try {
                BaiduMapRoutePlan.openBaiduMapDrivingRoute(para, getApplicationContext());
                BaiduMapRoutePlan.setSupportWebRoute(true);
            } catch (Exception e) {
                e.printStackTrace();
//                showDialog();
            }


            return true;
        });
    }

    /**
     * �����Ѿ���װ�ĵ�ͼ
     * @param latitude
     * @param longitude
     * @param zoom
     * @param addr
     */
    private void navigation(double latitude, double longitude, int zoom, String addr) {
        Log.e("tag","�����Ѿ���װ�ĵ�ͼ");
        StringBuffer sb = new StringBuffer();
        sb.append("geo:").append(latitude).append(",").append(latitude)
                .append("?").append("z=").append(zoom).append("?").append("q=")
                .append(addr);
        Uri mUri = Uri.parse(sb.toString());
        Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
        startActivity(mIntent);
    }

    /**
     * ��ȡ��λȨ��
     */
    private void addLocalPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                if(!ActivityCompat.shouldShowRequestPermissionRationale(ServiceActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)) {
//                    Toast.makeText(context,"���ѽ�ֹ��λȨ�ޣ����ֶ�????",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                ActivityCompat.requestPermissions(BaiduLocation.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_LOCATION);
                return;
            }
            initLocal();
        } else {
            initLocal();
        }
    }

    private void initLocal() {
        locationClient = new LocationClient(getApplicationContext());
        LocationClientOption lOptions = new LocationClientOption();
        lOptions.setCoorType("bd09ll"); //��������??
        lOptions.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//�߾��ȶ�λģ??
        lOptions.setOpenGps(true); //��gpsоƬ
        lOptions.setIsNeedAddress(true); //??Ҫ��λ�ĵ�ַ��Ϣ?? ʡ???�С��أ���)���ֵ���
        lOptions.setNeedDeviceDirect(true); //??Ҫ��λ�ķ���

        locationClient.setLocOption(lOptions);
        locationClient.registerLocationListener(this);


        locationClient.start();
    }

    /**
     * ���ñ��
     *
     * @param w γ��
     * @param l ����
     */
    public void setMarker(double w, double l) {
        curr_location = new LatLng(w, l);
/**
 * ����ͼ�������ҽ���λ����������
 */
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(curr_location)
                .zoom(18)
                .build();
        //����MapStatusUpdate�����Ա�������ͼ״̬��Ҫ�����ı仯
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //�ı��ͼ״̬
        baiduMap.animateMapStatus(mMapStatusUpdate);


        MarkerOptions option1 = new MarkerOptions()
                .position(curr_location)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.local));
        option1.animateType(MarkerOptions.MarkerAnimateType.drop);
        if (mPoints != null) {
            mPoints.remove();
        }
        if (baiduMap != null) {
            mPoints = (Marker) baiduMap.addOverlay(option1);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Constant.REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Intent intent = new Intent(context, MipcaActivityCapture.class);
//                    startActivityForResult(intent, 1);
                    initLocal();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.unRegisterLocationListener(this);
        locationClient.stop();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {

        String mProvince = bdLocation.getProvince();
        mCity = bdLocation.getCity();
        String mDistrict = bdLocation.getDistrict();
        String mStreet = bdLocation.getStreet();
        Log.e("tag", mProvince + "--" + mCity + "--" + mDistrict + "--" + mStreet);

//        mTv.setText(mProvince + city + mDistrict + mStreet);
        mTv.setText(bdLocation.getAddress().address);


        //����
        mLocation_longt = bdLocation.getLongitude();
        //γ��
        mLocation_lat = bdLocation.getLatitude();

        mTv_jing.setText("��γ�ȣ�" + mLocation_longt + "-" + mLocation_lat);
        setMarker(mLocation_lat, mLocation_longt);

    }
}
