package com.api.models.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
    private String userName;
    private String password;
}
