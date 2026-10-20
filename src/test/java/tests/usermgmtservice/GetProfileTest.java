package tests.usermgmtservice;

import base.AuthService;
import base.UserMgmtService;
import io.restassured.response.Response;
import models.response.LoginResponse;
import models.response.UserProfileResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestCredentials;

public class GetProfileTest {

    private final AuthService authService = new AuthService();
    private final UserMgmtService userMgmtService = new UserMgmtService();

    @Test(groups = "user", description = "Authenticated user can view their profile")
    public void getProfileReturnsCurrentUser() {
        Response loginResponseRaw = authService.login(TestCredentials.validLogin());
        Assert.assertEquals(loginResponseRaw.getStatusCode(), 200, "Login should succeed");

        LoginResponse loginResponse = loginResponseRaw.as(LoginResponse.class);
        Assert.assertNotNull(loginResponse.getToken(), "Login should return a token");

        Response profileResponse = userMgmtService.getProfile(loginResponse.getToken());
        Assert.assertEquals(profileResponse.getStatusCode(), 200, "Profile request should succeed");

        UserProfileResponse profile = profileResponse.as(UserProfileResponse.class);
        Assert.assertNotNull(profile.getUserName(), "Profile should contain a username");
        Assert.assertFalse(profile.getUserName().isBlank(), "Username should not be blank");
    }

    @Test(groups = "user", description = "Profile endpoint rejects requests without a token")
    public void getProfileWithoutTokenIsRejected() {
        Response response = userMgmtService.getProfileWithoutAuth();

        Assert.assertTrue(response.getStatusCode() >= 400 && response.getStatusCode() < 500,
                "An unauthenticated profile request should return a client error");
    }
}
