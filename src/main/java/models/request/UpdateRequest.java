package models.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
