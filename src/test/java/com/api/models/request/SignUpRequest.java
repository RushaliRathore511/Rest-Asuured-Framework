package com.api.models.request;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class SignUpRequest {
    private String userName;
    private String email;
    private String firstName;
    private String password;
    private String lastName;
    private String mobileNumber;
}
