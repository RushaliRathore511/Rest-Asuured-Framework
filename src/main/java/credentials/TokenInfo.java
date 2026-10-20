package credentials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TokenInfo {
    public String accessToken;
    public boolean isTokenExpired;

}
