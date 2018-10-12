package com.anylogic.iot.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.anylogic.iot",
			sqlSessionFactoryRef="sqlSessionFactory")
public class DataSourceConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Bean
    @Primary
    //@ConfigurationProperties(prefix="spring.datasource.main")
    public DataSource dataSource() {
    	
    	 BasicDataSource dataSource = new BasicDataSource();
    	 dataSource.setDriverClassName("org.postgresql.Driver");
         //dataSource.setUrl("jdbc:postgresql://192.168.110.4:5432/seiandb");
    	 dataSource.setUrl("jdbc:postgresql://anylogic.iptime.org:5432/postgres");
         dataSource.setUsername("postgres");
         dataSource.setPassword("new1234!@");
         return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
    	LOGGER.debug("> transactionManager");

    	return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
    	LOGGER.debug("> sqlSessionFactory");

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis.xml"));
        return bean.getObject();
    }
    
    public String decryption(){
    
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("encryptkey");
        
		String res = decryptor.decrypt("IKpyQNUbB5u3aSnDMr4VQyN/dMJWlG+8");
		//String res = decryptor.decrypt("UKK4Vw269f6/wwXMQSFhFg==");
		return res;


    }


}


