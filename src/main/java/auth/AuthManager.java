package auth;

import client.AuthClient;
import credentials.TokenInfo;
import models.request.LoginRequest;
import models.response.LoginResponse;

public class AuthManager  {

    //AuthManager manages the token with ThreadLocal
    private static final AuthClient authClient = new AuthClient();

    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public void authenticate(LoginRequest request) {
        LoginResponse response = authClient.login(request);
        String authToken = response.getToken();
        token.set(authToken);
    }

    //in case of using thread local
    public void clearToken() {
        token.remove();
    }

    //in case of using thread local
   /* public String getToken() {
        token.get();
    }*/

    //AuthManager manages/caches the token

    private static final Object refreshLock = new Object();
    private static volatile TokenInfo tokenInfo ;

    public static String getToken(){
        if(tokenInfo!=null &&  !tokenInfo.isTokenExpired()) {
            return tokenInfo.getAccessToken();
        }

        synchronized (refreshLock){
            // Check again because another thread
            // may have refreshed it while we waited.
            if(tokenInfo==null || tokenInfo.isTokenExpired()){
                tokenInfo =  authClient.generateToken();
            }

            return tokenInfo.getAccessToken();
        }
    }

}
