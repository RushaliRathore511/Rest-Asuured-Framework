package tests.credentials;

import credentials.TokenInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TokenInfoTest {

    @Test
    public void tokenWithFutureExpiryCanBeReused() {
        TokenInfo tokenInfo = new TokenInfo("sample-token", "2099-01-01T00:00:00Z");

        Assert.assertEquals(tokenInfo.getAccessToken(), "sample-token");
        Assert.assertFalse(tokenInfo.isTokenExpired());
    }

    @Test
    public void tokenPastExpiryNeedsRefresh() {
        TokenInfo tokenInfo = new TokenInfo("old-token", "2000-01-01T00:00:00Z");

        Assert.assertTrue(tokenInfo.isTokenExpired());
    }

    @Test
    public void epochExpiryIsSupported() {
        TokenInfo tokenInfo = new TokenInfo("old-token", "946684800");

        Assert.assertTrue(tokenInfo.isTokenExpired());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidExpiryIsRejected() {
        new TokenInfo("sample-token", "not-a-timestamp");
    }
}
