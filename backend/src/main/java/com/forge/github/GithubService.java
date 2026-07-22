package com.forge.github.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.forge.github.dto.GithubAccessTokenResponse;
import java.util.Map;

@Service
public class GithubService {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Value("${github.client.secret}")
private String clientSecret;

    public String getGithubAuthorizationUrl() {

        return "https://github.com/login/oauth/authorize"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=repo,user";
    }
    private final RestClient restClient;
    public GithubService(RestClient restClient) {
    this.restClient = restClient;
}
public GithubAccessTokenResponse exchangeCode(String code) {

    return restClient.post()
            .uri("https://github.com/login/oauth/access_token")
            .header("Accept", "application/json")
            .body(Map.of(
                    "client_id", clientId,
                    "client_secret", clientSecret,
                    "code", code,
                    "redirect_uri", redirectUri
            ))
            .retrieve()
            .body(GithubAccessTokenResponse.class);
}
}
