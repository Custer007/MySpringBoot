package com.mr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@Slf4j
@Order(0)
public class MyApplicationRunner implements ApplicationRunner {

    private final DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public MyApplicationRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("系统启动完成前执行MyApplicationRunner流程");
        printDataSourceConfig();
        testDataSourceConnection();
    }

    /**
     * 打印数据库连接配置信息（注意：生产环境建议对密码脱敏）
     */
    private void printDataSourceConfig() {
        log.info("========== 数据库连接配置信息 ==========");
        log.info("URL      : {}", url);
        log.info("Username : {}", username);
        log.info("Password : {}", password);
        log.info("Driver   : {}", driverClassName);
        log.info("========================================");
    }

    /**
     * 实际获取一个数据库连接，验证配置是否正确可用
     */
    private void testDataSourceConnection() {
        try (Connection connection = dataSource.getConnection()) {
            log.info("数据库连接成功，实际连接URL: {}", connection.getMetaData().getURL());
        } catch (Exception e) {
            log.error("数据库连接失败: {}", e.getMessage());
        }
    }
}
