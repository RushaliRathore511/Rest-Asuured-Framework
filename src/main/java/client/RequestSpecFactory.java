package client;

import auth.AuthManager;
import config.ConfigManager;
import filter.LoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecFactory {

    private RequestSpecFactory() {
    }

    public static RequestSpecification getDefaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .setContentType(ContentType.JSON)
                .addFilter(new LoggingFilter())
                .build();
    }

    // Uses the existing cached client token managed by AuthManager.
    public static RequestSpecification getAuthenticatedSpec() {
        return getAuthenticatedSpec(AuthManager.getToken());
    }

    // For flows where the caller has already authenticated with username and password.
    public static RequestSpecification getAuthenticatedSpec(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("An access token is required for an authenticated request");
        }

        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .addFilter(new LoggingFilter())
                .build();
    }
}
