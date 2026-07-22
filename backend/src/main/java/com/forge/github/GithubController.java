package com.forge.github.controller;

import com.forge.github.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URI;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/api/v1/github/login")
    public ResponseEntity<Void> githubLogin() {

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(githubService.getGithubAuthorizationUrl()))
                .build();
    }
    @GetMapping("/api/v1/github/callback")
public String callback(@RequestParam String code) {

    return githubService.exchangeCode(code).access_token();
}
}