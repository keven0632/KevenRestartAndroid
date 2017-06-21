package com.cihon.androidrestart_keven.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.cihon.androidrestart_keven.R;

import java.util.ArrayList;
import java.util.List;

public class BaiduMapLine extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mMap;
    String[] gpsItem = {"114.34229|23.048237", "114.34229|23.048237", "114.34229|23.048237", "114.34229|23.048235", "114.34229|23.048235", "114.34229|23.048235", "114.34229|23.048235", "114.34229|23.048235", "114.34229|23.048235", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048233", "114.34229|23.048231", "114.34229|23.048231", "114.34229|23.048231", "114.34229|23.04823", "114.34229|23.04823", "114.34229|23.04823", "114.34229|23.04823", "114.34229|23.04823", "114.34229|23.04823", "114.34229|23.048227", "114.34229|23.048227", "114.34229|23.048227", "114.34229|23.048227", "114.34229|23.048227", "114.34229|23.048227", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048225", "114.34229|23.048223", "114.34229|23.048223", "114.34229|23.048223", "114.34229|23.048223", "114.34229|23.048223", "114.34229|23.048223", "114.34229|23.048222", "114.34229|23.048222", "114.34229|23.048222", "114.34229|23.048218", "114.34229|23.048218", "114.34229|23.048218", "114.34229|23.04822", "114.34229|23.04822", "114.34229|23.04822", "114.342285|23.048222", "114.342285|23.048222", "114.342285|23.048222", "114.34227|23.048222", "114.34227|23.048222", "114.34227|23.048222", "114.342255|23.04821", "114.342255|23.04821", "114.342255|23.04821", "114.34226|23.048203", "114.34226|23.048203", "114.34226|23.048203", "114.34226|23.048206", "114.34226|23.048206", "114.34226|23.048206", "114.342255|23.048191", "114.342255|23.048191", "114.342255|23.048191", "114.34223|23.048187", "114.34223|23.048187", "114.34223|23.048187", "114.34217|23.048187", "114.34217|23.048187", "114.34217|23.048187", "114.34206|23.04818", "114.34206|23.04818", "114.34206|23.04818", "114.341896|23.04819", "114.341896|23.04819", "114.341896|23.04819", "114.34173|23.048178", "114.34173|23.048178", "114.34173|23.048178", "114.341576|23.048176", "114.341576|23.048176", "114.341576|23.048176", "114.34146|23.048176", "114.34146|23.048176", "114.34146|23.048176", "114.34132|23.048172", "114.34132|23.048172", "114.34132|23.048172", "114.34126|23.048168", "114.34126|23.048168", "114.34126|23.048168", "114.34124|23.048044", "114.34124|23.048044", "114.34124|23.048044", "114.34125|23.047855", "114.34125|23.047855", "114.34125|23.047855", "114.34125|23.047693", "114.34125|23.047693", "114.34125|23.047693", "114.34123|23.047602", "114.34123|23.047602", "114.34123|23.047602", "114.341125|23.047565", "114.341125|23.047565", "114.341125|23.047565", "114.34101|23.047573", "114.34101|23.047573", "114.34101|23.047573", "114.340965|23.04752", "114.340965|23.04752", "114.340965|23.04752", "114.340965|23.047483", "114.340965|23.047483", "114.340965|23.047483", "114.340965|23.047482", "114.340965|23.047482", "114.340965|23.047482", "114.34097|23.04749", "114.34097|23.04749", "114.34097|23.04749", "114.341|23.0475", "114.341|23.0475", "114.341|23.0475", "114.34104|23.04751", "114.34104|23.04751", "114.34104|23.04751", "114.34108|23.047504", "114.34108|23.047504", "114.34108|23.047504", "114.3411|23.047516", "114.3411|23.047516", "114.3411|23.047516", "114.34107|23.047518", "114.34107|23.047518", "114.34107|23.047518", "114.34094|23.047476", "114.34094|23.047476", "114.34094|23.047476", "114.34093|23.047354", "114.34093|23.047354", "114.34093|23.047354", "114.34098|23.047165", "114.34098|23.047165", "114.34098|23.047165", "114.34106|23.046934", "114.34106|23.046934", "114.34106|23.046934", "114.34115|23.046637", "114.34115|23.046637", "114.34115|23.046637", "114.341255|23.046343", "114.341255|23.046343", "114.341255|23.046343", "114.341415|23.046114", "114.341415|23.046114", "114.341415|23.046114", "114.341644|23.046003", "114.341644|23.046003", "114.341644|23.046003", "114.34191|23.04597", "114.34191|23.04597", "114.34191|23.04597"};
    private MarkerOptions option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_baidu_map_line);


        mMapView = (MapView) findViewById(R.id.mapview);
        mMap = mMapView.getMap();
        LatLngBounds.Builder builder1 = new LatLngBounds.Builder();

        List<LatLng> points = new ArrayList<>();

        for (int i = 0; i < gpsItem.length; i++) {

            String[] lalos = gpsItem[i].split("\\|");
            LatLng pt1 = new LatLng(Double.parseDouble(lalos[1]), Double.parseDouble(lalos[0]));
            CoordinateConverter converter = new CoordinateConverter();
            converter.from(CoordinateConverter.CoordType.GPS);
            converter.coord(pt1);
            pt1 = converter.convert();
            points.add(pt1);//点元素
            builder1.include(pt1);
        }


        if (points.size() > 1) {
            OverlayOptions ooPolyline = new PolylineOptions().width(7).color(Color.parseColor("#2DC9D7")).points(points);
            mMap.addOverlay(ooPolyline);

            MarkerOptions option1 = new MarkerOptions()
                    .position(new LatLng(points.get(0).latitude, points.get(0).longitude))
                    .icon(BitmapDescriptorFactory
                            .fromAssetWithDpi("Icon_end.png"));
            mMap.addOverlay(option);
            option = new MarkerOptions()
                    .position(new LatLng(points.get(points.size() - 1).latitude, points.get(points.size() - 1).longitude))
                    .icon(BitmapDescriptorFactory
                            .fromAssetWithDpi("Icon_start.png"));
            mMap.addOverlay(option);
            mMap.addOverlay(option1);

            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(points.get(points.size()/2))
                    .zoom(18)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mMap.animateMapStatus(mMapStatusUpdate);

//            mMap.setMapStatus(MapStatusUpdateFactory
//                    .newLatLngBounds(builder1.build()));


        }
    }
}
