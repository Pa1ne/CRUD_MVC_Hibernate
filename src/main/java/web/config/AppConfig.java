package web.config;


import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;

import java.util.Properties;

import javax.sql.DataSource;


@Configuration
@ComponentScan("web")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class AppConfig {

   private final Environment env;

   public AppConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource dataSource() throws PropertyVetoException {
      ComboPooledDataSource dataSource = new ComboPooledDataSource();
      dataSource.setDriverClass(env.getProperty("db.driver"));
      dataSource.setJdbcUrl(env.getProperty("db.url"));
      dataSource.setUser(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws PropertyVetoException {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(dataSource());
      entityManagerFactoryBean.setPackagesToScan("web");

      Properties props = new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

      entityManagerFactoryBean.setJpaProperties(props);
      JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
      entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
      return entityManagerFactoryBean;
   }

   @Bean
   public PlatformTransactionManager transactionManager() throws PropertyVetoException {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
      return transactionManager;
   }

   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }
}
