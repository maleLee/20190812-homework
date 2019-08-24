package com.lee.homework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 9:00
 * @Description
 **/
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String nodes;
    private String maxAttmpts;
    private String expire;
    private String commandTimeout;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMaxAttmpts() {
        return maxAttmpts;
    }

    public void setMaxAttmpts(String maxAttmpts) {
        this.maxAttmpts = maxAttmpts;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(String commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}
