package credentials;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class TokenInfo {

    private final String accessToken;
    private final Instant expiresAt;

    public TokenInfo(String accessToken, String expiresAt) {
        if (accessToken == null || accessToken.isBlank()) {
            throw new IllegalArgumentException("Access token must not be empty");
        }
        this.accessToken = accessToken;
        this.expiresAt = parseExpiry(expiresAt);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public boolean isTokenExpired() {
        // Refresh a little before expiry to avoid using a token at the boundary.
        return !Instant.now().isBefore(expiresAt.minusSeconds(30));
    }

    private static Instant parseExpiry(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Token response must include expiresAt");
        }

        try {
            return Instant.parse(value);
        } catch (DateTimeParseException ignored) {
            try {
                return OffsetDateTime.parse(value).toInstant();
            } catch (DateTimeParseException ignoredAgain) {
                try {
                    long epochValue = Long.parseLong(value);
                    // Support common epoch-seconds and epoch-milliseconds responses.
                    return Math.abs(epochValue) >= 100_000_000_000L
                            ? Instant.ofEpochMilli(epochValue)
                            : Instant.ofEpochSecond(epochValue);
                } catch (NumberFormatException | java.time.DateTimeException e) {
                    throw new IllegalArgumentException(
                            "Unsupported expiresAt format. Use ISO-8601 or epoch seconds/milliseconds.", e);
                }
            }
        }
    }
}
