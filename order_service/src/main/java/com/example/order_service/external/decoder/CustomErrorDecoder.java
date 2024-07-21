package com.example.order_service.external.decoder;

import com.example.order_service.exception.CustomException;
import com.example.order_service.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper =new ObjectMapper();
        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
            return new CustomException(errorResponse.getMessage(),errorResponse.getErrorCode(),response.status());
        } catch (IOException e) {
            throw new CustomException("Internal server error", "INTERNAL_SERVER_ERROR", 500);
        }
    }
}
