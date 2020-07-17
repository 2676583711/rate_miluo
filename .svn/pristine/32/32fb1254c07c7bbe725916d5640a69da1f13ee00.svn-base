package com.rate.system.rate_system.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.JdbcConstants;
import org.apache.commons.lang3.BooleanUtils;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.*;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by PrimaryKey on 17/2/4.
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Configuration
public class DruidConfig {
    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);
    
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", ""); //白名单
        return reg;
    }
/*	@Bean
	public ServletRegistrationBean druidStatViewServle() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		servletRegistrationBean.setName("druidStatViewServle");
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// servletRegistrationBean.addInitParameter("deny","192.168.1.73");//黑名单，与白名单同时存在的时候以白名单为准
//		servletRegistrationBean.addInitParameter("loginUsername", "gack");
//		servletRegistrationBean.addInitParameter("loginPassword", "gack2016");
		return servletRegistrationBean;
	}*/
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.setName("druidStatFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	@Bean(name = "beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer(Environment env) {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage(env.getProperty("beetlsql.basePackage"));
		conf.setDaoSuffix(env.getProperty("beetlsql.daoSuffix"));
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return conf;
	}

//	@DependsOn({"datasource"})
//	@Bean(name = "sqlManagerFactoryBean")
//	@Primary
//	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource, Environment env) {
//		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
//		System.out.println(env.toString());
//		BeetlSqlDataSource source = new BeetlSqlDataSource();
//		source.setMasterSource(datasource);
//		factory.setCs(source);
//		factory.setDbStyle(selectDB(env.getProperty("jdbc.type")));
//		factory.setInterceptors(new Interceptor[0]);
//		factory.setNc(new UnderlinedNameConversion());
//		factory.setSqlLoader(new ClasspathLoader(env.getProperty("beetlsql.sqlPath")));
//		return factory;
//	}

    @DependsOn({"datasource"})
    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource, Environment env) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        System.out.println(env.toString());
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        factory.setCs(source);
        factory.setDbStyle(selectDB(env.getProperty("jdbc.type")));
        factory.setInterceptors(new Interceptor[] {new DebugInterceptor()});
        factory.setNc(new UnderlinedNameConversion());
        factory.setSqlLoader(new ClasspathLoader(env.getProperty("beetlsql.sqlPath")));
        return factory;
    }

	private DBStyle selectDB(String style) {
		switch (style) {
		case JdbcConstants.MYSQL:
			return new MySqlStyle();
		case JdbcConstants.ORACLE:
			return new OracleStyle();
		case JdbcConstants.DB2:
			return new DB2SqlStyle();
		case JdbcConstants.SQL_SERVER:
			return new SqlServerStyle();
		case JdbcConstants.POSTGRESQL:
			return new PostgresStyle();
		case JdbcConstants.H2:
			return new H2Style();
		case JdbcConstants.SQLITE:
			return new SQLiteStyle();
			
		default:
			return null;
		}
	}
	
	@Bean(name = "datasource")
	@Primary
	public DataSource druidDataSource(Environment env) throws SQLException {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDbType(env.getProperty("jdbc.type"));
        datasource.setUrl(env.getProperty("jdbc.url"));
        datasource.setUsername(env.getProperty("jdbc.username"));
        datasource.setPassword(env.getProperty("jdbc.password"));
        datasource.setDriverClassName(env.getProperty("jdbc.driver"));

        //configuration
        datasource.setInitialSize(Integer.valueOf(env.getProperty("jdbc.initialSize")));
        datasource.setMinIdle(Integer.valueOf(env.getProperty("jdbc.minIdle")));
        datasource.setMaxActive(Integer.valueOf(env.getProperty("jdbc.maxActive")));
        datasource.setMaxWait(Integer.valueOf(env.getProperty("jdbc.maxWait")));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("jdbc.timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("jdbc.minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(env.getProperty("jdbc.validationQuery"));
        datasource.setTestWhileIdle(BooleanUtils.toBoolean(env.getProperty("jdbc.testWhileIdle")));
        datasource.setTestOnBorrow(BooleanUtils.toBoolean(env.getProperty("jdbc.testOnBorrow")));
        datasource.setTestOnReturn(BooleanUtils.toBoolean(env.getProperty("jdbc.testOnReturn")));
        datasource.setPoolPreparedStatements(BooleanUtils.toBoolean(env.getProperty("jdbc.poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("jdbc.initialSize")));
        try {
            datasource.setFilters(env.getProperty("jdbc.type"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(env.getProperty("jdbc.type"));

        return datasource;

	}
}

