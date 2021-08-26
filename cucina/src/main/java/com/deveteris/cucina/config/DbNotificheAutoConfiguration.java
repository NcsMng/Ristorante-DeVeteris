package com.deveteris.cucina.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:notifications-db.properties"})
@EnableJpaRepositories(
        basePackages = {"com.deveteris.notificationsmanager.repository"},
        entityManagerFactoryRef = "notificheEntityManager",
        transactionManagerRef = "notificheTransactionManager")
public class DbNotificheAutoConfiguration {
    private final Environment env;

    public DbNotificheAutoConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    @Qualifier("notificheDataSource")
//    @ConfigurationProperties(prefix="notifiche")
    public DataSource notificheDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("notifiche.spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("notifiche.spring.datasource.url"));
        dataSource.setUsername(env.getProperty("notifiche.spring.datasource.username"));
        dataSource.setPassword(env.getProperty("notifiche.spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @Qualifier("notificheEntityManager")
    public LocalContainerEntityManagerFactoryBean notificheEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(notificheDataSource());
        em.setPackagesToScan("com.deveteris.notificationsmanager.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
               "update");
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Qualifier("notificheTransactionManager")
    public PlatformTransactionManager notificheTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                notificheEntityManager().getObject());
        return transactionManager;
    }
}
