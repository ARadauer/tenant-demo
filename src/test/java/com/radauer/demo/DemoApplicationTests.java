package com.radauer.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests
{

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testEmptyTenant()
  {
    assertThat(this.restTemplate.getForObject(getBaseUrl(), String.class)).contains("Hello World! 0");
  }

  @Test
  void testHuTenant()
  {
    String url = UriComponentsBuilder.fromUriString(getBaseUrl())
      .queryParam("tenant", "hu").toUriString();

    assertThat(this.restTemplate.getForObject(url, String.class)).contains("Helló Világ! 10");
  }

  @Test
  void testAtTenant()
  {
    String url = UriComponentsBuilder.fromUriString(getBaseUrl())
      .queryParam("tenant", "at").toUriString();

    assertThat(this.restTemplate.getForObject(url, String.class)).contains("Hello World! 0");
  }

  private String getBaseUrl()
  {
    return "http://localhost:" + port + "/greeting";
  }

}
