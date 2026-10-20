package auth;

import client.AuthClient;
import credentials.TokenInfo;
import models.request.LoginRequest;
import models.response.LoginResponse;

public class AuthManager {

    // User/password token is kept per TestNG worker thread.
    private static final AuthClient authClient = new AuthClient();
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public void authenticate(LoginRequest request) {
        LoginResponse response = authClient.login(request);
        String authToken = response.getToken();

        if (authToken == null || authToken.isBlank()) {
            throw new IllegalStateException("Login response did not contain a token");
        }
        token.set(authToken);
    }

    public void clearToken() {
        token.remove();
    }

    // Shared client-credentials token cache.
    private static final Object refreshLock = new Object();
    private static volatile TokenInfo tokenInfo;

    public static String getToken() {
        if (tokenInfo != null && !tokenInfo.isTokenExpired()) {
            return tokenInfo.getAccessToken();
        }

        synchronized (refreshLock) {
            // Another thread may have refreshed the token while we waited.
            if (tokenInfo == null || tokenInfo.isTokenExpired()) {
                tokenInfo = authClient.generateToken();
            }
            return tokenInfo.getAccessToken();
        }
    }
}
