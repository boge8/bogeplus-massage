package com.bogeplus.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.order.dto.OrderGaodeDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderGaodeAPI {
    private static final String API_KEY = "425515ff8d2e941cfe9d69c06943d7a2";
    private static final String BASE_URL_TAXI = "https://restapi.amap.com/v5/direction/driving";
//    private static final String BASE_URL_TRANSIT = "https://restapi.amap.com/v3/direction/transit/integrated";

    public static OrderGaodeDTO getGaodeTaxiDTO(String origin, String destination)throws Exception{
        String url = BASE_URL_TAXI + "?key=" + API_KEY + "&origin=" + origin + "&destination=" + destination + "&strategy=0&output=JSON";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        if ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        //使用fastJSON解析JSON字符串
        JSONObject jsonResponse = JSONObject.parseObject(response.toString());
        //获取路径信息
        if (!jsonResponse.get("info").equals("OK")) {
            throw new BizException("高德地图API调用失败,错误码：" + jsonResponse.get("infocode"));
        }
        OrderGaodeDTO dto = new OrderGaodeDTO();
        JSONObject routeObject = jsonResponse.getJSONObject("route");
        String taxiCostString = routeObject.get("taxi_cost").toString();
        BigDecimal taxiCost = new BigDecimal(taxiCostString);

        JSONArray paths = routeObject.getJSONArray("paths");
        JSONObject path = paths.getJSONObject(0);
        String distanceString = path.get("distance").toString();
        BigDecimal distanceM = new BigDecimal(distanceString);
        BigDecimal divisor = new BigDecimal("1000");
        BigDecimal distanceKM = distanceM.divideToIntegralValue(divisor);

        dto.setCost(taxiCost);
        dto.setDistance(distanceKM);
        return dto;
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(
//                getGaodeTaxiDTO("116.481028,39.989643","116.434446,39.90816"));
//    }
}
