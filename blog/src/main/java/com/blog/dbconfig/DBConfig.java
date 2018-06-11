package com.blog.dbconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.blog")
@EnableTransactionManagement
public class DBConfig {

	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("system");
		dataSource.setPassword("oracle123");
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder lsfb=new LocalSessionFactoryBuilder(dataSource);
		lsfb.addProperties(getHibernateProperties());
		lsfb.scanPackages("com.blog.domain");
		return lsfb.buildSessionFactory();
	}
	
	private Properties getHibernateProperties()
	{
		Properties prop=new Properties();
		prop.put("hibernate.hbm2ddl.auto","update");//HibernateMappingFile(hbm),DataDescriptionLanguage(ddl)
		                                            //possible values=create,create:drop,validate,update
		prop.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		prop.put("hibernate.show_sql","true");
		prop.put("hibernate.format_sql","true");
		return prop;
	}
	@Bean
	public HibernateTransactionManager getTransaction(SessionFactory sessionFactory)
	{
		return new HibernateTransactionManager(sessionFactory);
	}
}
