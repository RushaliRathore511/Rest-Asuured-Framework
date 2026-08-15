package com.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testng.annotations.Ignore;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String token;
    private String type;
    private int id;
    private String userName;
    private String email;
    private List<String> userRoles;
}
