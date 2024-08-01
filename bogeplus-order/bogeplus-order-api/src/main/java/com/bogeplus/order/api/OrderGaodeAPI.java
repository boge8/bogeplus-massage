package com.bogeplus.order.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bogeplus.common.constant.order.OrderConstant;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.order.dto.GaodeParamDTO;
import com.bogeplus.order.dto.OrderGaodeDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderGaodeAPI {
    private static final String API_KEY = "425515ff8d2e941cfe9d69c06943d7a2";
    private static final String BASE_URL_TAXI = "https://restapi.amap.com/v5/direction/driving";
    private static final String BASE_URL_TRANSIT = "https://restapi.amap.com/v3/direction/transit/integrated";

    public static OrderGaodeDTO getGaodeTaxiDTO(GaodeParamDTO gaodeParamDTO)throws Exception{
        String origin = gaodeParamDTO.getOrigin();
        String destination = gaodeParamDTO.getDestination();
        String cityCode = gaodeParamDTO.getCityCode();
        String travelMode = gaodeParamDTO.getTravelMode();

        String url = travelMode.equals(OrderConstant.TAXI)
        ?BASE_URL_TAXI + "?key=" + API_KEY + "&origin=" + origin + "&destination=" + destination + "&strategy=0&output=JSON"
        :BASE_URL_TRANSIT + "?key=" + API_KEY + "&origin=" + origin + "&destination=" + destination + "&city=" + cityCode + "&strategy=0&output=JSON";

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
        if (!jsonResponse.get("info").toString().equals("OK")) {
            if(jsonResponse.get("infocode").toString().equals("20000")){
                throw new BizException("公交暂不支持跨省");
            }
            throw new BizException("高德地图API调用失败,错误码：" + jsonResponse.get("infocode"));
        }


        //获取路径信息
        OrderGaodeDTO orderGaodeDTO = new OrderGaodeDTO();
        JSONObject routeObject = jsonResponse.getJSONObject("route");
        //获取出行费用
        String costString;
        String distanceString;
        if (travelMode.equals(OrderConstant.TAXI)) {
            costString = routeObject.get("taxi_cost").toString();
            JSONArray paths = routeObject.getJSONArray("paths");
            JSONObject path = paths.getJSONObject(0);
            distanceString = path.get("distance").toString();
        }else {
            JSONArray transits = routeObject.getJSONArray("transits");
            JSONObject transit = transits.getJSONObject(0);
            costString = transit.get("cost").toString();
            distanceString= transit.get("distance").toString();
        }

        BigDecimal cost = new BigDecimal(costString);
        BigDecimal distanceM = new BigDecimal(distanceString);
        BigDecimal divisor = new BigDecimal("1000");
        BigDecimal distanceKM = distanceM.divideToIntegralValue(divisor);

        orderGaodeDTO.setCost(cost);
        orderGaodeDTO.setDistance(distanceKM);
        return orderGaodeDTO;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                getGaodeTaxiDTO(new GaodeParamDTO("112.626271,37.796694","112.604346,37.796690","028","2")));
    }
}
