package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String token;
    private String type;
    private String accessToken;
    private String expiresAt;
    private int id;
    private String userName;
    private String email;
    private List<String> userRoles;
}
