package com.example.sns_test;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class FriendFragment_siwon extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2=inflater.inflate(R.layout.fragment_friend, container, false);

        MapView mapView = new MapView(getActivity());

        ViewGroup mapViewContainer = (ViewGroup) v2.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 앱 최초 실행시 중심점 변경 (현 위치 상명대 천안캠퍼스)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(36.833725, 127.179120), true);

        //마커
        MapPOIItem marker = new MapPOIItem();

        //배열좌표
        double[] x = {36.831637,36.830424,36.832779,36.832808,36.833667,36.833022,36.830402,36.832903,};
        double[] y = {127.177179,127.177110,127.175677,127.176270,127.173143,127.175711,127.177311,127.174691};

        //배열의 크기
        double[] array = new double[8];

        //가게 이름
        String Name[] = {"흥부반점","명장국밥","서브밀","모야모야", "천호지", "별이네 식당", "조가연 마라탕", "부안집"};

        //맵 포인트 위도경도 설정
        for(int i=0; i<array.length; i++){
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x[i], y[i]);
            marker.setItemName((Name[i]));

            marker.setTag(0);
            marker.setMapPoint(mapPoint);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            mapView.addPOIItem(marker);

        }

        return v2;
    };

}

