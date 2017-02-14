package example.cors;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 跨域自动配置条件
 * 当配置文件中cors.enable为true则满足自动配置跨域条件
 *
 * Created by null on 2017/2/14.
 */
public class CorsCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final String name = "cors.enable";
        final boolean enable = Boolean.parseBoolean(context.getEnvironment().getProperty(name));
        if (enable) {
            return new ConditionOutcome(true, "cors.enable is true");
        }
        return new ConditionOutcome(false, "cors.enable is false");
    }

}
