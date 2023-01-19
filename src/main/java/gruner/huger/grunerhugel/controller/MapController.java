package gruner.huger.grunerhugel.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class MapController {

    @Autowired
    private LandRepository landRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FarmRepository farmRepository;

    @GetMapping(value = "/maper")
    @ResponseBody
    public String updateForm(String y, String x) throws IOException {

        System.out.println("alo");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://geocode.maps.co/reverse?lat=" + y
                        + "&lon=" + x + "&format=json"))
                .build();

        String village = "N/A";

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
                    try {
                        village = address.getString("town");
                    } catch (JSONException u) {
                        GrunerhugelApplication.logger.info("No village found");
                    }
                }
            }
            System.out.println(village);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return village;
    }

    @GetMapping(value = "/maper/load")
    @ResponseBody
    public String loadLands() {
        GrunerhugelApplication.logger.info("Loading lands");
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Farm farm = farmRepository.findByUser(user);
        if (farm != null) {
            Iterable<Land> lista = landRepository.findByFarm(farm);
            return toJSON(lista);
        }
        return null;
    }

    String toJSON(Iterable<Land> list) {
        // set list into Json without Gson
        JSONArray jsonArray = new JSONArray();
        for (Land land : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("y", land.getLatitude());
            jsonObject.put("x", land.getLongitude());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

}
