package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        String url = "http://94.198.50.185:7081/api/users";
        RestTemplate restTemplate = new RestTemplate();
        String user = restTemplate.getForObject(url, String.class);
       String cookie = restTemplate.headForHeaders(url).get("Set-Cookie").toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Set-Cookie",cookie);
        System.out.println(headers.get("Set-Cookie"));
        Map<String,String> map = new HashMap<>();
       map.put("id","3");
       map.put("name","James");
       map.put("lastname","Brown");
       map.put("age","20");
       User user1 = new User(3L,"James","Brown",(byte)20);

HttpEntity<User> entity = new HttpEntity<>(user1, headers);
ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        System.out.println(responseEntity.getBody().toString());
    }
}
