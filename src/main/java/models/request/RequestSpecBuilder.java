package models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestSpecBuilder {

    private String baseUrl;
    private String contentType;
}
