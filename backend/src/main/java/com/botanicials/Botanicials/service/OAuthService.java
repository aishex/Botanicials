package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuthService extends DefaultOAuth2UserService{

    private final UserRepository userRepository;

    public OAuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String googleId = oAuth2User.getAttribute("sub");
        String imageUrl = oAuth2User.getAttribute("picture");

        User user = userRepository.findByEmail(email)
                .orElseGet(User::new);

        user.setEmail(email);
        user.setName(name);
        user.setGoogleId(googleId);
        user.setImageUrl(imageUrl);

        userRepository.save(user);
        return oAuth2User;
    }
}