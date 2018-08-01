package org.gleeman;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yjf on 2018/6/27.
 */
@Component
@Data
@ConfigurationProperties("profile.test")
public class JdbcProperties1 {
private String username;
private  String password;
}
