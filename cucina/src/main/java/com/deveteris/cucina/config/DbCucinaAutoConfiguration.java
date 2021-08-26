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
@PropertySource({"classpath:cucina-db.properties"})
@EnableJpaRepositories(
        basePackages = {"com.deveteris.cucina.repository"},
        entityManagerFactoryRef = "cucinaEntityManager",
        transactionManagerRef = "cucinaTransactionManager")
public class DbCucinaAutoConfiguration {
    private final Environment env;

    public DbCucinaAutoConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
//    @Primary
    @Qualifier("cucinaDataSource")
    public DataSource cucinaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("cucina.spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("cucina.spring.datasource.url"));
        dataSource.setUsername(env.getProperty("cucina.spring.datasource.username"));
        dataSource.setPassword(env.getProperty("cucina.spring.datasource.password"));
        return dataSource;
    }

    @Bean
//    @Primary
    @Qualifier("cucinaEntityManager")
    public LocalContainerEntityManagerFactoryBean cucinaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(cucinaDataSource());
        em.setPackagesToScan("com.deveteris.cucina.model");

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
//    @Primary
    @Qualifier("cucinaTransactionManager")
    public PlatformTransactionManager cucinaTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                cucinaEntityManager().getObject());
        return transactionManager;
    }
}
