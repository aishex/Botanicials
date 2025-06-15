package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)

@WebMvcTest(OAuthController.class)
class OAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // --- GET /auth/google ---
    @Test
    void testRedirectToGoogleAuth() throws Exception {
        mockMvc.perform(get("/auth/google"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/oauth2/authorization/google"));
    }

    // --- GET /auth/me - user not authenticated ---
    @Test
    void testGetMe_Unauthenticated() throws Exception {
        SecurityContext context = Mockito.mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(context);

        mockMvc.perform(get("/auth/me"))
                .andExpect(status().isUnauthorized());
    }

    // --- GET /auth/me - user not found in DB ---
    @Test
    void testGetMe_UserNotFound() throws Exception {
        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getName()).thenReturn("notfound@example.com");

        SecurityContext context = Mockito.mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        when(userService.findByEmail("notfound@example.com")).thenReturn(null);

        mockMvc.perform(get("/auth/me"))
                .andExpect(status().isNotFound());
    }

    // --- GET /auth/me - user authenticated and exists ---
    @Test
    void testGetMe_Success() throws Exception {
        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getName()).thenReturn("test@example.com");

        SecurityContext context = Mockito.mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("Test User");

        when(userService.findByEmail("test@example.com")).thenReturn(user);

        mockMvc.perform(get("/auth/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value("test@example.com"))
                .andExpect(jsonPath("$.user.name").value("Test User"));
    }

    // --- POST /auth/logout ---
    @Test
    void testLogout_Redirect() throws Exception {
        mockMvc.perform(post("/auth/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logout"));
    }
}
