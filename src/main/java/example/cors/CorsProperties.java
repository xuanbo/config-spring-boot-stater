package example.cors;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 跨域属性
 *
 * Created by null on 2017/2/13.
 */
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private String mapping = "/**";

    private String[] allowedOrigins = {"*"};

    private String[] allowedMethods = {"GET", "POST"};

    private String allowCredentials = "true";

    private String maxAge = "3600";

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String[] allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public String getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(String allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }
}
