package com.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class UserProfileResponse {
    private String type;
    private int id;
    private String userName;
    private String email;
}
