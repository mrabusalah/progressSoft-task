package com.mytask.transfermoney.auth0;

import com.mytask.transfermoney.module.Account;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken oAuth2AccessToken,
            OAuth2Authentication oAuth2Authentication) {

        Object principal = oAuth2Authentication.getPrincipal();

        Map<String, Object> additionalInformation = new HashMap<>();

        if (principal instanceof Account) {
            Account account = (Account) principal;

            additionalInformation.put("userId", account.getId());
            additionalInformation.put("username", account.getClientUsername());
        }

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInformation);

        return oAuth2AccessToken;
    }
}
