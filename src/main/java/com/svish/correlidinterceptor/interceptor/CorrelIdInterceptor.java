package com.svish.correlidinterceptor.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CorrelIdInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID_HEADER_NAME = "Svish-CorrelId";

    Logger logger = LoggerFactory.getLogger(CorrelIdInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String validCorrelId = this.validateCorrelId(
                    request.getHeader(CORRELATION_ID_HEADER_NAME), response
            );
            MDC.put(CORRELATION_ID_HEADER_NAME, validCorrelId);
            response.setHeader(CORRELATION_ID_HEADER_NAME, validCorrelId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return true;
    }

    private String validateCorrelId(String correlId, HttpServletResponse response) throws Exception {
        if (!StringUtils.isBlank(correlId) && !correlId.equals("generate")) {
            if (!correlId.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")) {
                String newCorrelId = generateCorrelId();
                MDC.put(CORRELATION_ID_HEADER_NAME, newCorrelId);
                response.setHeader(CORRELATION_ID_HEADER_NAME, newCorrelId);
                throw new Exception("Invalid input correlation id: " + correlId);
            }
        } else {
            correlId = generateCorrelId();
        }
        return correlId;
    }

    private String generateCorrelId() {
        return UUID.randomUUID().toString();
    }
}
