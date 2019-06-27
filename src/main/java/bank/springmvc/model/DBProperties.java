package bank.springmvc.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@ConfigurationProperties("db")
public class DBProperties {

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.driverclassname);
        dataSource.setUrl(this.getUrl());
        dataSource.setUsername(this.getUsername());
        dataSource.setPassword(this.getPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    private static String driverclassname;
    private static String username;
    private static String password;
    private static String url;
    private static String table;

    public static String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public static String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public static String getDriverclassname() {
        return driverclassname;
    }
    public void setDriverclassname(String driverclassname) {
        this.driverclassname = driverclassname;
    }
    public static String getTableName() {
        return table;
    }
    public void setTableName(String table) {
        this.table = table;
    }
    @Override
    public String toString() {
        return "DBProperties =  [username=" + username + ", password=" + password + ", url=" + url + "]";
    }


}