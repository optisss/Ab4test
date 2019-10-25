package com.ab4system.extremesport;

import com.ab4system.extremesport.model.Sport;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ExtremeSportApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SportTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    @Test
    public void contextLoads() {
    }
    @Test
    public void testGetAllSports() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/sport/all",
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }


    @Test
    public void testCreateUser() {
        Sport sport = new Sport();
        sport.setName("testSport");
        sport.setPrice(20.2);
        sport.setStartDate(LocalDateTime.now());
        sport.setEndDate(LocalDateTime.now().plusDays(30));
        ResponseEntity<Sport> postResponse = restTemplate.postForEntity(getRootUrl() + "/sport/create", sport, Sport.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

}
