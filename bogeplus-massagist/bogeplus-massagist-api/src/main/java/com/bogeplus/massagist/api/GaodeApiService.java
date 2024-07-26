package com.bogeplus.massagist.api;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

/**
 * @Auther: xu
 * @Date: 2024/7/25 - 07 - 25 - 下午12:28
 * @Description: com.bogeplus.massagist.api
 * @version: 1.0
 */
@Service
public class GaodeApiService {

    private static final String GAODE_API_KEY = "your_api_key";
    private static final String GAODE_POI_SEARCH_URL = "https://restapi.amap.com/v3/place/text";

    public String getCoordinates(String address) throws Exception {
        String url = GAODE_POI_SEARCH_URL + "?key=" + GAODE_API_KEY + "&keywords=" + address + "&output=JSON";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            String response = httpClient.execute(request, responseHandler -> EntityUtils.toString(responseHandler.getEntity()));
            return parseCoordinates(response);
        }
    }

    private String parseCoordinates(String response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode poisNode = rootNode.path("pois");
        if (poisNode.isArray() && poisNode.size() > 0) {
            JsonNode firstPoi = poisNode.get(0);
            String location = firstPoi.path("location").asText();
            return location;
        }
        return null;
    }
}
