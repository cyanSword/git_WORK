package org.gleeman;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by yjf on 2018/6/27.
 */
@Component
@Data
@Profile("test")
public class JdbcProperties {
@Value("${profile.test.username}")
private String username;
@Value("${profile.test.password}")
private  String password;
}
