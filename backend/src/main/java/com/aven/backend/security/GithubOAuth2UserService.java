package com.aven.backend.security;

import com.aven.backend.model.User;
import com.aven.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserService userService;
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User githubUser = delegate.loadUser(request);
        String accessToken = request.getAccessToken().getTokenValue();
        request.getAccessToken().getScopes();
        String scopes = String.join(",", request.getAccessToken().getScopes());
        User user = userService.upsertFromGithub(githubUser.getAttributes(), accessToken, scopes);
        return new AppUserPrincipal(user, githubUser.getAttributes());
    }
}
