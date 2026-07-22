package com.forge.github.dto;

public record GithubAccessTokenResponse(
        String access_token,
        String token_type,
        String scope
) {}