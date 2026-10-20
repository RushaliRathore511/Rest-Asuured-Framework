package tests.usermgmtservice;

import base.AuthService;
import base.UserMgmtService;
import models.request.LoginRequest;
import models.response.LoginResponse;
import models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetProfileTest extends UserMgmtService {

    UserMgmtService userMgmtService = new UserMgmtService();
    AuthService aUthService = new AuthService();

    @Test(description = "Verify get profile API")
    public void getProfileTest(){
        LoginRequest request = LoginRequest.builder().userName("testuser").password("testpass").build();
         Response response = aUthService.login(request);
         LoginResponse loginResponse = response.as(LoginResponse.class);

        System.out.println(loginResponse.getToken());
        Response response1 = userMgmtService.getProfile(loginResponse.getToken());

        UserProfileResponse userProfileResponse = response1.as(UserProfileResponse.class);
        System.out.println(userProfileResponse.getUserName());

    }

}
