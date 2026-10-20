# Rest Assured + Selenium test framework

A small Java automation project with API coverage and a separate browser UI suite. Existing token management and credential-based authentication are kept in the framework; the tests use service classes and shared request specifications.

## Stack
Java 17, Maven, Rest Assured, TestNG, Selenium WebDriver and Log4j2.

## Run the tests

Use JDK 17 and Maven. Chrome is required for the UI suite.

```bash
# API smoke suite: login and profile
mvn test

# Isolated token expiry unit tests (no API server needed)
mvn test -Dsuite=unit

# Broader API suite: signup, password reset and profile update
mvn test -Dsuite=regression

# Browser suite in headless Chrome
mvn test -Dsuite=ui -Dheadless=true
```

The UI tests use [The Internet's demo login page](https://the-internet.herokuapp.com/login). They cover successful login, invalid username, invalid password, blank username, blank password and logout. Selenium Manager resolves ChromeDriver.

## API configuration

The QA base URL is in `src/test/resources/config/qa.properties`. Make sure the service is available before running API tests. Override the URL without editing the file:

```bash
API_BASE_URL=https://your-qa-api.example mvn test
```

The sample login tests default to `testuser` / `testpass`; for your own QA account, set `API_TEST_USERNAME` and `API_TEST_PASSWORD`.

The client-credentials token flow in `AuthManager` uses the existing `TokenInfo` cache and needs `CLIENT_ID`, `CLIENT_SECRET`, and `AUTH_URL` environment variables. `AUTH_URL` must be the token endpoint URL. It can alternatively be set as `auth.url` in the selected environment properties. The sample `secret.properties` file is retained for project continuity; do not place real credentials in a tracked file.

The regression suite creates a signup record and sends a password-reset request; its profile-update test also changes the selected test account's email and phone. Run it only against an isolated QA environment. The configured sample QA host was not reachable during the latest check, so API behavior still needs to be verified against a working test service.

## Layout

- `src/main/java/auth` and `src/main/java/credentials` — token management and credential access
- `src/main/java/client` — shared API client and request specifications
- `src/main/java/base` — API service classes
- `src/main/java/models` — request and response models
- `src/main/java/config` — environment configuration
- `src/test/java/tests` — API tests, token tests and test credential helper
- `src/test/java/ui` — login Page Object
- `src/test/resources/suites` — API smoke, API regression, unit and UI suites
