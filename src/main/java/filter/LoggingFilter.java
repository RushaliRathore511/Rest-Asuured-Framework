package filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext context) {
        String requestUri = requestSpec.getURI();
        int queryStart = requestUri.indexOf('?');
        if (queryStart >= 0) {
            requestUri = requestUri.substring(0, queryStart);
        }

        // Do not log request headers, bodies, tokens or query-parameter values.
        logger.info("Request: {} {}", requestSpec.getMethod(), requestUri);
        Response response = context.next(requestSpec, responseSpec);
        logger.info("Response: {} ({} ms)", response.getStatusCode(), response.getTime());
        return response;
    }
}
