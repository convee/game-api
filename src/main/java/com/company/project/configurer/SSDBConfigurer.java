package com.company.project.configurer;

import lombok.Getter;
import lombok.Setter;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.SSDB;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.ssdb") // 从配置文件中读取
@Setter
@Getter
public class SSDBConfigurer {

    private static SSDB ssdb = null;

    private String host;

    private int port;

    private int timeout;

    private String auth;

    /**
     * 必须要设置一个空的构造函数
     */
    public SSDBConfigurer() {

    }

    public SSDBConfigurer(String host, int port, int timeout, String auth) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.auth = auth;
    }

    /**
     * 指定配置生成一个使用连接池的客户端
     */
    @Bean
    public SSDB getSSDB() {
        if (ssdb == null) {
            //测试环境支持auth认证
            if (this.getAuth() != null && !"".equals(this.getAuth())) {
                ssdb = SSDBs.pool(this.host, this.port, this.timeout, null, this.auth.getBytes());
            } else {
                ssdb = SSDBs.pool(this.host, this.port, this.timeout, null);
            }
        }
        return ssdb;
    }

}
