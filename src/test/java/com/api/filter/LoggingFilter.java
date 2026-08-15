package com.api.filter;


import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingFilter implements Filter {

    public static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        logRequest(requestSpec);
      Response response =  ctx.next(requestSpec,responseSpec); //request is going to executed
      logResponse(response);

        return response; //test assertion
    }

    public void logRequest(FilterableRequestSpecification requestSpec){
        logger.info("End point " + requestSpec.getBasePath());
        logger.info("Headers " + requestSpec.getHeaders());
        logger.info("Body " + requestSpec.getBody());
    }

    public void logResponse(Response response){
        logger.info("Status Code " + response.getStatusCode());
        logger.info("Headers " + response.getHeaders());
        logger.info("Body " + response.getBody().prettyPrint());
    }
}
