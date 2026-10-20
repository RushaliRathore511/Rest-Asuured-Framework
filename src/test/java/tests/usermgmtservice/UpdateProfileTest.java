package tests.usermgmtservice;

import base.AuthService;
import base.UserMgmtService;
import io.restassured.response.Response;
import models.request.UpdateRequest;
import models.response.LoginResponse;
import models.response.UserProfileResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestCredentials;

public class UpdateProfileTest {

    private final AuthService authService = new AuthService();
    private final UserMgmtService userMgmtService = new UserMgmtService();

    @Test(groups = "user", description = "Authenticated user can update profile details")
    public void updateProfileWithValidDetailsSucceeds() {
        Response loginResponseRaw = authService.login(TestCredentials.validLogin());
        Assert.assertEquals(loginResponseRaw.getStatusCode(), 200, "Login should succeed");

        LoginResponse loginResponse = loginResponseRaw.as(LoginResponse.class);
        Assert.assertNotNull(loginResponse.getToken(), "Login should return a token");

        String suffix = Long.toString(System.currentTimeMillis());
        String email = "portfolio+" + suffix + "@example.com";
        String mobileNumber = "9" + suffix.substring(suffix.length() - 9);

        UpdateRequest request = UpdateRequest.builder()
                .firstName("Portfolio")
                .lastName("Tester")
                .email(email)
                .mobileNumber(mobileNumber)
                .build();

        Response updateResponse = userMgmtService.updateProfile(loginResponse.getToken(), request);
        Assert.assertTrue(updateResponse.getStatusCode() >= 200 && updateResponse.getStatusCode() < 300,
                "Profile update should succeed");

        // Verify the change through the read endpoint rather than trusting only the update response.
        Response profileResponse = userMgmtService.getProfile(loginResponse.getToken());
        Assert.assertEquals(profileResponse.getStatusCode(), 200, "Profile should still be accessible");

        UserProfileResponse updatedProfile = profileResponse.as(UserProfileResponse.class);
        Assert.assertEquals(updatedProfile.getEmail(), email,
                "A subsequent profile read should return the updated email");
    }
}
