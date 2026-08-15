package com.api.tests.usermgmtservice;

import com.api.base.AUthService;
import com.api.base.UserMgmtService;
import com.api.models.request.LoginRequest;
import com.api.models.request.UpdateRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import com.beust.ah.A;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UpdateProfileTest extends UserMgmtService {

    UserMgmtService userMgmtService = new UserMgmtService();
AUthService aUthService = new AUthService();

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
