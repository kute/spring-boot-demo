package com.kute.demo.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * created by kute on 2018/08/02 20:55
 */
public class ConditionalOnProfileBean extends SpringBootCondition {

    /**
     * 返回true才可以创建
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String message = "ConditionalOnProfile Message ";
        String annName = ConditionalOnProfile.class.getName();
        // 未 添加此注解，默认可以创建
        if (!metadata.isAnnotated(annName)) {
            return onReturn(true, message);
        }
        MultiValueMap<String, Object> annotationAttributes = metadata.getAllAnnotationAttributes(annName);
        Object value = annotationAttributes.get("value");
        // 未 指明 profile 也可以创建
        if(null == value || "".equalsIgnoreCase(String.valueOf(value))) {
            return onReturn(true, message);
        }
        String[] profiles = context.getEnvironment().getActiveProfiles();
        if(null == profiles || profiles.length == 0) {
            return onReturn(true, message);
        }
        Collection<String> collection = new ArrayList<>();
        if (value instanceof Collection) {
            Collection<String[]> vs = (Collection<String[]>) value;
            for (String[] ss : vs) {
                for (String s : ss) {
                    collection.add(s);
                }
            }
        } else {
            //collection = Arrays.asList((String[]) value);
            for (String s : (String[]) value) {
                collection.add(s);
            }
        }
        return onReturn(match(collection, profiles), message);
    }

    private boolean match(Collection<?> collection, String[] profiles) {
        for (String profile : profiles) {
            if (collection.contains(profile)) return false;
        }
        return true;
    }

    private ConditionOutcome onReturn(boolean match, String message) {
        return new ConditionOutcome(match, ConditionMessage.of(message));
    }
}
