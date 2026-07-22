package com.forge.github.controller;

import com.forge.github.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/api/v1/github/login")
    public String githubLogin() {
        return githubService.getGithubAuthorizationUrl();
    }
}