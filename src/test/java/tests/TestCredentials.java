package tests;

import models.request.LoginRequest;

public final class TestCredentials {

    private TestCredentials() {
    }

    public static LoginRequest validLogin() {
        String username = System.getenv().getOrDefault("API_TEST_USERNAME", "testuser");
        String password = System.getenv().getOrDefault("API_TEST_PASSWORD", "testpass");

        return LoginRequest.builder()
                .userName(username)
                .password(password)
                .build();
    }
}
