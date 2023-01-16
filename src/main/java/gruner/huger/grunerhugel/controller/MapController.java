package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MapController {

    @GetMapping(value ="/maper")
    public String updateForm(String y, String x) throws IOException {

        System.out.println("alo");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://eu1.locationiq.com/v1/reverse?key=pk.c675dbdf6f9753f9619dc1ae844560a4&lat="+y+"&lon="+x+"&format=json")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        String list[] = mine.split(",");
        System.out.println(list[7]);
        System.out.println("key");
        return "simulation";
    }

}
