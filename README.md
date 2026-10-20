# Rest Assured + Selenium test framework

This is a small Java automation project covering API checks and a browser login flow. The API code uses service classes and a shared request-spec factory; the UI tests use a Page Object so page actions stay separate from assertions.

## Stack
Java 17, Maven, Rest Assured, TestNG, Selenium WebDriver and Log4j2.

## Run the tests

Use JDK 17 and Maven. Chrome is needed for the UI suite.

```bash
# API smoke suite: login and profile
mvn test

# API regression suite: signup, password reset and profile update
mvn test -Dsuite=regression

# Token expiry unit tests
mvn test -Dsuite=unit

# UI suite (headless Chrome)
mvn test -Dsuite=ui -Dheadless=true
```

The UI tests use the public demo login page at [The Internet](https://the-internet.herokuapp.com/login). They cover a successful login, invalid username, invalid password, blank username, blank password and logout. Selenium Manager handles ChromeDriver setup.

## Authentication and configuration

The original authentication structure is retained:
- `AuthManager` keeps the per-thread login token and the shared client-credentials token cache.
- `TokenInfo` holds the access token and its expiry time; the cache refreshes shortly before expiry.
- `CredentialsProvider` reads `CLIENT_ID` and `CLIENT_SECRET` from environment variables.
- `RequestSpecFactory` supplies either a default request specification or an authenticated one.

Set `CLIENT_ID`, `CLIENT_SECRET`, and `AUTH_URL` in your local environment before using the client-credentials token flow. `AUTH_URL` must be the actual token endpoint expected by your QA environment. Do not put real credentials in source control. The tracked `src/main/resources/secret.properties` only contains placeholder values and is not read by `CredentialsProvider`.

The QA base URL is in `src/test/resources/config/qa.properties`. You can override it without changing the file:

```bash
API_BASE_URL=https://your-qa-api.example mvn test
```

The login tests use the sample account `testuser` / `testpass` by default. Override it for your QA environment with `API_TEST_USERNAME` and `API_TEST_PASSWORD`. Use a dedicated test account and never put a real password or token in the repository.

The regression suite creates a new signup record and sends a password-reset request, and the profile-update test changes the test account's details. Run it only against an isolated QA environment. The configured QA backend was not reachable during the latest check, so API behavior still needs verification against a working environment.

## Layout

- `src/main/java/base` — API service classes
- `src/main/java/client` — shared API client and request specifications
- `src/main/java/auth` and `src/main/java/credentials` — existing token manager and token model
- `src/main/java/models` — request and response models
- `src/main/java/config` — environment configuration
- `src/test/java/tests` — API tests and token model tests
- `src/test/java/ui` — login Page Object
- `src/test/resources/suites` — API smoke, API regression, token unit and UI suites
