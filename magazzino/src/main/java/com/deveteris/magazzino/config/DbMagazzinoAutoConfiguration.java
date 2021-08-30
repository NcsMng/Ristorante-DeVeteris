package com.deveteris.magazzino.config;

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

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:magazzino-db.properties"})
@EnableJpaRepositories(
        basePackages = {"com.deveteris.magazzino.repository"},
        entityManagerFactoryRef = "magazzinoEntityManager",
        transactionManagerRef = "magazzinoTransactionManager")
public class DbMagazzinoAutoConfiguration {
    private final Environment env;

    public DbMagazzinoAutoConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    @Qualifier("magazzinoDataSource")
    public DataSource magazzinoDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("magazzino.spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("magazzino.spring.datasource.url"));
        dataSource.setUsername(env.getProperty("magazzino.spring.datasource.username"));
        dataSource.setPassword(env.getProperty("magazzino.spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @Qualifier("magazzinoEntityManager")
    public LocalContainerEntityManagerFactoryBean magazzinoEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(magazzinoDataSource());
        em.setPackagesToScan("com.deveteris.magazzino.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                "update");
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5InnoDBDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Qualifier("magazzinoTransactionManager")
    public PlatformTransactionManager magazzinoTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                magazzinoEntityManager().getObject());
        return transactionManager;
    }
}
