package com.test.collector.config.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("com.test.collector.repository")
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	private Environment env;

	@Value("${mybatis.mapper-locations}")
	private String mapperLocation;
	
	@Value("${mybatis.type-aliases-package}")
	private String aliasPkg;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		sessionFactory.setMapperLocations(resolver.getResources(mapperLocation));
		sessionFactory.setVfs(SpringBootVFS.class);
		sessionFactory.setTypeAliasesPackage(aliasPkg);
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setMapUnderscoreToCamelCase(true);
		sessionFactory.setConfiguration(config);
		sessionFactory.afterPropertiesSet();
		return sessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
		return sqlSessionTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
		return manager;
	}
	
	@Bean
	@Primary
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:log4jdbc:postgresql://database-1.cue2lnhpxxlj.ap-northeast-2.rds.amazonaws.com:5432/postgres?currentSchema=public");
		dataSource.setUsername("test3");
		dataSource.setPassword("test3");
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		dataSource.setMinimumIdle(20);
		dataSource.setMaximumPoolSize(20);
		dataSource.setConnectionTimeout(30000);
		dataSource.setIdleTimeout(600000);
		dataSource.setMaxLifetime(1800000);
		dataSource.setValidationTimeout(5000);

		return new HikariDataSource(dataSource);
//        return new LazyConnectionDataSourceProxy(dataSource);
	}

}
