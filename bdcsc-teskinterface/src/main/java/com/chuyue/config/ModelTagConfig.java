package com.chuyue.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * 模型层任务
 */
@Component
@PropertySource("classpath:model-Tag.yml")
@Getter
@Setter
public class ModelTagConfig {
    @Value("${tag}")
    private String tag;
}
