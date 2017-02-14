package example.cors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Created by null on 2017/2/13.
 */
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
@ConditionalOnWebApplication
@ConditionalOnClass(CorsFilter.class)
@ConditionalOnProperty(prefix = "cors", value = "enable", matchIfMissing = false)
public class CorsAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CorsAutoConfiguration.class);

    private final CorsProperties corsProperties;

    public CorsAutoConfiguration(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    /**
     * 跨域配置条件满足时，则自动配置跨域filter
     *
     * @return CorsFilter
     */
    @Bean
    @Conditional(CorsCondition.class)
    public CorsFilter corsFilter() {
        return new CorsFilter(corsProperties);
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        final String corsFilterName = "corsFilter";
        final String urlPatterns = "/*";
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns(urlPatterns);
        registration.setName(corsFilterName);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("Mapping filter: '{}' to: [{}]", corsFilterName, urlPatterns);
        return registration;
    }
}
