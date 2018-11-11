package com.dhirajprakash.vm.config;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import srikant.shakti.model.PlacaDetails;
import srikant.shakti.model.ReportMetadata;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.dhirajprakash.vm.config" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier("hbernateproperties")
	private Properties hbernateproperties;

	@Bean
	@Primary
	@Autowired
	public HibernateTransactionManager transactionManagerPrimary(@Qualifier("sessionFactory") SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Autowired
	@Qualifier("dataSource")
	private DataSource datasource;

	@Bean(name = { "sessionFactory" })
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		try {
			sessionFactory.setDataSource(datasource);
			sessionFactory.setPackagesToScan(new String[] { "srikant.shakti.model" });
			sessionFactory.setHibernateProperties(hbernateproperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	@Bean(name = { "dataSource" })
	public DataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("database.driver"));
		dataSource.setUrl(environment.getRequiredProperty("database.url"));
		dataSource.setUsername(environment.getRequiredProperty("database.user"));
		dataSource.setPassword(environment.getRequiredProperty("database.password"));
		return dataSource;
	}

	@Bean(name = { "trans" })
	@Autowired
	public HibernateTransactionManager transactionManager(@Qualifier("sessionFactory") SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	/*------------------------HIBERNATE PROPERTY FOR CONFUGURATION---------------------------*/
	@Bean(name = { "hbernateproperties" })
	public Properties hbernateproPertiessqlserver() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.jdbc.batch_size", String.valueOf(5));
		/*
		 * properties.put("hibernate.hbm2ddl.auto","create");
		 * properties.put("hibernate.hbm2ddl.import_files","/initial_data.sql")
		 */;
		return properties;
	}

	/*
	 * @RequestMapping(value = { "getDataWeakely"}, method = RequestMethod.POST)
	 * public List<ReportMetadata> getDataWeakely(HttpServletResponse
	 * response,WebRequest webRequest){ SQLQuery q =
	 * sessionFactory.getCurrentSession().
	 * createSQLQuery("select occurencia_day,count(occurencia_day) from report_metadata group by occurencia_day;"
	 * ); return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list(); }
	 * 
	 * @RequestMapping(value = { "getpleacedetail"}, method = RequestMethod.POST)
	 * public List<PlacaDetails> getPleaceDetail(HttpServletResponse
	 * response,WebRequest webRequest){ Criteria cr =
	 * sessionFactory.getCurrentSession().createCriteria(PlacaDetails.class);
	 * List<PlacaDetails> pleaceDetail = cr.list(); return pleaceDetail; }
	 */
	*/
}
