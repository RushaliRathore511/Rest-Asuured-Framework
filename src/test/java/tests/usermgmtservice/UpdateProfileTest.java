package tests.usermgmtservice;

import base.AuthService;
import base.UserMgmtService;
import models.request.LoginRequest;
import models.request.UpdateRequest;
import models.response.LoginResponse;
import models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UpdateProfileTest extends UserMgmtService {

    UserMgmtService userMgmtService = new UserMgmtService();
AuthService aUthService = new AuthService();

    @Test(description = "Verify get profile API")
    public void getProfileTest(){
        LoginRequest request = LoginRequest.builder().userName("testuser").password("testpass").build();
        Response response = aUthService.login(request);
        LoginResponse loginResponse = response.as(LoginResponse.class);

        UpdateRequest updateRequest = UpdateRequest.builder().firstName("").lastName("").email("").mobileNumber("").build();
        Response response1 = userMgmtService.updateProfile(loginResponse.getToken(),updateRequest);
        UserProfileResponse userProfileResponse = response1.as(UserProfileResponse.class);

        System.out.println(userProfileResponse.getUserName());

    }
}
