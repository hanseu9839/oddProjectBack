package com.odd.oddProject.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.odd.oddProject", sqlSessionFactoryRef = "SqlSessionFactory")
public class MyBatisConfig {


    @Value("${mybatis.mapper-locations}")
    String mPath;

    @Value("${mybatis.config-location}")
    String mybatisConfigPath;

    @Bean(name= "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(DataSource);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources(mPath);
        sqlSessionFactoryBean.setMapperLocations(res);
        sqlSessionFactoryBean.setConfigLocation(
                                new PathMatchingResourcePatternResolver()
                                        .getResource(mybatisConfigPath));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory")SqlSessionFactory firstSqlSessionFactory){
            return new SqlSessionTemplate(firstSqlSessionFactory);
    }
}
