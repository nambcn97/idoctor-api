package com.fpt.idoctor.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.fpt.idoctor.model.User;

public class CustomTokenEnhancer implements TokenEnhancer{

	@Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> additionalInfo = new HashMap<>();

        User user = (User)((UserPrincipal) authentication.getPrincipal()).getUser();

        additionalInfo.put("role", user.getRole().getCode());
        additionalInfo.put("fullName", user.getFullname());
        additionalInfo.put("token", accessToken.getValue());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}
