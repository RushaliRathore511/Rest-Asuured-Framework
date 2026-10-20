package models.response;
import lombok.Data;
@Data
public class UserProfileResponse {
    private String type;
    private int id;
    private String userName;
    private String email;
}
