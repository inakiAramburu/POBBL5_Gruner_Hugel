package gruner.huger.grunerhugel.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.io.JsonEOFException;

import gruner.huger.grunerhugel.GrunerhugelApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class MapController {

    @GetMapping(value = "/maper")
    public String updateForm(String y, String x) throws IOException {

        System.out.println("alo");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://geocode.maps.co/reverse?lat=" + y
                        + "&lon=" + x + "&format=json"))
                .build();

        String village = "";

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            System.out.println(obj);
            JSONObject address = obj.getJSONObject("address");
            try {
                village = address.getString("village");
            } catch (JSONException e) {
                try {
                    village = address.getString("city");
                } catch (JSONException r) {
                    GrunerhugelApplication.logger.info("No village found");
                }
            }
            System.out.println(village);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return village;
    }

}
