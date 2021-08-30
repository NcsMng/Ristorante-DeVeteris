package com.deveteris.clientservices.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:permissions-db.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.deveteris.permessi.repository"},
        entityManagerFactoryRef = "permessiEntityManager",
        transactionManagerRef = "permessiTransactionManager")
public class DbPermessiAutoConfiguration {

    private final Environment env;

    public DbPermessiAutoConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    @Qualifier("permessiDataSource")
    public DataSource permessiDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.permessi-datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.permessi-datasource.url"));
        dataSource.setUsername(env.getProperty("spring.permessi-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.permessi-datasource.password"));
        return dataSource;
    }

    @Bean
    @Qualifier("permessiEntityManager")
    public LocalContainerEntityManagerFactoryBean permessiEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(permessiDataSource());
        em.setPackagesToScan("com.deveteris.permessi.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Qualifier("permessiTransactionManager")
    public PlatformTransactionManager permessiTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                permessiEntityManager().getObject());
        return transactionManager;
    }
}
