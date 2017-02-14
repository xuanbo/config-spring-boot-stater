package example.cors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域Filter
 *
 * Created by null on 2017/2/13.
 */
public class CorsFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(CorsFilter.class);

    private final CorsProperties corsProperties;

    public CorsFilter(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String currentOrigin = httpRequest.getHeader("Origin");
        log.debug("当前访问来源是：{}", currentOrigin);
        // 如果当前访问来源在application.properties的Access-Control-Allow-Origin配置范围内，则允许访问，否则不允许
        String[] allowedOrigins = corsProperties.getAllowedOrigins();
        if(currentOrigin != null) {
            for (int i = 0; i < allowedOrigins.length; i++) {
                if ("*".equals(allowedOrigins[i])) {
                    httpResponse.setHeader("Access-Control-Allow-Origin", currentOrigin);
                } else if(currentOrigin.equals(allowedOrigins[i])) {
                    httpResponse.setHeader("Access-Control-Allow-Origin", currentOrigin);
                }
            }
        } else { // 对于无来源的请求(比如在浏览器地址栏直接输入请求的)，那就只允许我们自己的机器可以吧
            httpResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1");
        }
        String allowMethods = StringUtils.arrayToDelimitedString(corsProperties.getAllowedMethods(),",");
        httpResponse.setHeader("Access-Control-Allow-Methods", allowMethods);
        httpResponse.setHeader("Access-Control-Allow-Credentials", corsProperties.getAllowCredentials());
        httpResponse.setHeader("Access-Control-Max-Age", corsProperties.getMaxAge());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
