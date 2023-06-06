package com.xyz.filter;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * logback日志添加traceId
 * @author xyz
 * 2023/5/30
 */
@Configuration
public class LogFilter extends OncePerRequestFilter {

    private final  static String MDC_TRACE_ID = "traceId";

    /**
     * 生成一个tranceId
     * @return tranceId
     */
    public static String getMdcTraceId() {
        long currentTime = System.nanoTime();
        return String.join(":",MDC_TRACE_ID, String.valueOf(currentTime));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String traceId = getMdcTraceId();
        MDC.put(MDC_TRACE_ID,traceId);
        filterChain.doFilter(request,response);
        MDC.clear();
    }
}
