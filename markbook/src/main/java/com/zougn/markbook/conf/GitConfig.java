package com.zougn.markbook.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "git")
@Data
public class GitConfig {
    private String path;
    private String remoteUrl;
    private String username;
    private String password;
}

