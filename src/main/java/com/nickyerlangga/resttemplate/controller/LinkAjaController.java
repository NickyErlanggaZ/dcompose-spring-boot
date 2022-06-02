package com.nickyerlangga.resttemplate.controller;

import com.nickyerlangga.resttemplate.model.LinkAja;
import com.nickyerlangga.resttemplate.model.Post;
import com.nickyerlangga.resttemplate.service.LinkAjaService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
public class LinkAjaController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LinkAjaService linkAjaService;

    @RequestMapping(value = "/api/link-aja")
    public String getAllLinkAja(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "https://6205d489161670001741bd34.mockapi.io/link-aja/fetch", HttpMethod.GET,entity,String.class).getBody();
    }

    @RequestMapping(value = "/api/link-aja/{id}")
    public String getLinkAjaById(@PathVariable("id") String id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "https://6205d489161670001741bd34.mockapi.io/link-aja/fetch/"+id,HttpMethod.GET,entity,String.class).getBody();
    }

    @RequestMapping(value = "/api/link-aja/insert")
    public String insertToDatabase() throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        Long id;
        String avatar,name,createdAt;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String jsonString = restTemplate.exchange(
                "https://6205d489161670001741bd34.mockapi.io/link-aja/fetch", HttpMethod.GET,entity,String.class).getBody();
        JSONArray arr = new JSONArray(jsonString);
        for(int i=0;i<arr.length();i++) {
            id = arr.getJSONObject(i).getLong("id");
            avatar = arr.getJSONObject(i).getString("avatar");
            name = arr.getJSONObject(i).getString("name");
            createdAt = arr.getJSONObject(i).getString("createdAt");
//            createdAt = format.parse(arr.getJSONObject(i).getString("createdAt"));
            LinkAja data = new LinkAja();
            data.setId(id);
            data.setAvatar(avatar);
            data.setName(name);
            data.setCreatedAt(createdAt);
//            System.out.println("body "+i+": "+post.getBody());
            linkAjaService.addLinkAja(data);
        }
        return "success";
    }

}
