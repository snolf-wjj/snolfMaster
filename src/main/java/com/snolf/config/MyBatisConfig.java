package com.snolf.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/22
 * Time: 11:21
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer{

	@Autowired
	DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage("com.snolf.*.model");
		//分页插件
		PageHelper pageHelper = new PageHelper();
		Properties props = new Properties();
		// 3.3.0版本可用 - 分页参数合理化，默认false禁用
		//启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
		//禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
		props.setProperty("reasonable", "true");
		// 支持通过 Mapper 接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，
		// 自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页
		props.setProperty("supportMethodsArguments", "true");
		props.setProperty("returnPageInfo", "check");
		// 用于从对象中根据属性名取值， 可以配置 pageNum,pageSize,count,pageSizeZero,reasonable，
		// 不配置映射的用默认值， 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero
		props.setProperty("params", "count=countSql");
		pageHelper.setProperties(props);
		//添加插件
		bean.setPlugins(new Interceptor[]{pageHelper});
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			bean.setMapperLocations(resolver.getResources("classpath:mapper/*/*.xml"));
			bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);// 使用驼峰命名法转换字段
			return bean.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}