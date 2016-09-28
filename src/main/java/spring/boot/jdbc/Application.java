package spring.boot.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
This is an example of JDBC with Spring Boot.

To make it work, in pom.xml, uncomment 
1) <start-class>spring.boot.jdbc.Application</start-class> and comment out other <start-class>
2) mvn spring-boot:run
3) Try "http://localhost:8083/jdbc/tryjdbc"

http://stackoverflow.com/questions/20044292/spring-boot-how-to-externalize-jdbc-datasource-configuration

@EnableAutoConfiguration will do all the magic. 
It will detect spring-boot-starter-web dependency in pom.xml and start the servlet container (server).
It will read datasource information from application.properties
and create JdbcTemplate bean register in AnnotationConfigEmbeddedWebApplicationContext. This ApplicationContext basically has beans registered in Application class + JdbcTemplate and other implicit beans.


*/
@Configuration // This class becomes spring context
@ComponentScan // default package is this package. It will register all components as beans from this package.
@EnableAutoConfiguration // Without this, SpringApplication.run(...) will fail. It will give "Unable to start EmbeddedWebApplicationContext due to missing EmbeddedServletContainerFactory bean."
public class Application {

    public static void main(String args[]) {
        EmbeddedWebApplicationContext webApplicationContext = (EmbeddedWebApplicationContext)SpringApplication.run(Application.class, args);
        String[] beans = webApplicationContext.getBeanDefinitionNames();
        System.out.println("All Beans:");
        for(String bean:beans) {
            System.out.println(bean.toString());
        }

        System.out.println("------------------------------------");
        System.out.println("Configured Datasource properties:");
        DataSourceProperties dataSourceProperties=(DataSourceProperties)webApplicationContext.getBean("spring.datasource.CONFIGURATION_PROPERTIES");
        System.out.println("Data:"+dataSourceProperties.getData()+"\n"+"URL:"+dataSourceProperties.getUrl()+"\n"+"UserName:"+dataSourceProperties.getUsername()+"\n"+"Password:"+dataSourceProperties.getPassword());
        System.out.println("------------------------------------");
    }
}

/*
All Beans:
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
application -----------
org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor
org.springframework.context.annotation.ConfigurationClassPostProcessor.enhancedConfigurationProcessor
jdbcController ---------
myBean -------
myDAO -------
org.springframework.boot.autoconfigure.AutoConfigurationPackages
org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration
propertySourcesPlaceholderConfigurer
org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration$EmbeddedJetty -------
jettyEmbeddedServletContainerFactory -------
org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration
embeddedServletContainerCustomizerBeanPostProcessor
org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration$DispatcherServletConfiguration
dispatcherServlet -------
dispatcherServletRegistration
org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration
org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration
error
beanNameViewResolver -------
org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
errorAttributes
basicErrorController
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter$FaviconConfiguration
faviconHandlerMapping
faviconRequestHandler
org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
requestMappingHandlerMapping -------
mvcContentNegotiationManager -------
viewControllerHandlerMapping -------
beanNameHandlerMapping
resourceHandlerMapping
defaultServletHandlerMapping
requestMappingHandlerAdapter
mvcConversionService
mvcValidator
mvcUriComponentsContributor
httpRequestHandlerAdapter -------
simpleControllerHandlerAdapter
handlerExceptionResolver
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter ------ Due to 'spring-boot-starter-web' added in pom.xml
defaultViewResolver ------ Due to 'spring-boot-starter-web' added in pom.xml
requestContextListener ------ Due to 'spring-boot-starter-web' added in pom.xml
viewResolve ------ Due to 'spring-boot-starter-web' added in pom.xml
spring.mvc.CONFIGURATION_PROPERTIES ------ Due to 'spring-boot-starter-web' added in pom.xml
spring.resources.CONFIGURATION_PROPERTIES ------ Due to 'spring-boot-starter-web' added in pom.xml
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.store
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration ------ Due to 'spring-boot-starter-web' added in pom.xml
hiddenHttpMethodFilter
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$JdbcTemplateConfiguration ------- Due to 'spring-jdbc' added in pom.xml
jdbcTemplate ------- Due to 'spring-jdbc' added in pom.xml
namedParameterJdbcTemplate ------- Due to 'spring-jdbc' added in pom.xml
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$DataSourceInitializerConfiguration -------
dataSourceInitializer -------
org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration ------- Due to 'spring-jdbc' added in pom.xml
dataSource ------- Due to 'spring-jdbc' added in pom.xml
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$EmbeddedConfiguration  ------- Due to 'spring-jdbc' added in pom.xml
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration  ------- Due to 'spring-jdbc' added in pom.xml
spring.datasource.CONFIGURATION_PROPERTIES  ------- Due to 'spring-jdbc' added in pom.xml
dataSourceInitializerPostProcessor
org.springframework.context.annotation.MBeanExportConfiguration
mbeanExporter
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration$Empty
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
objectNamingStrategy
mbeanServer
org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration
org.springframework.transaction.config.internalTransactionAdvisor
transactionAttributeSource
transactionInterceptor
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration$TransactionManagementConfiguration
org.springframework.aop.config.internalAutoProxyCreator
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
transactionManager
org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration$ObjectMappers
mappingJackson2HttpMessageConverter
http.mappers.CONFIGURATION_PROPERTIES
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$JacksonObjectMapperAutoConfiguration
jacksonObjectMapper
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration
messageConverters
org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration
multipartConfigElement
multipartResolver
multipart.CONFIGURATION_PROPERTIES
org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration
serverProperties
*/
/*
// 

O/P:
Creating tables
Inserting customer record for John Woo
Inserting customer record for Jeff Dean
Inserting customer record for Josh Bloch
Inserting customer record for Josh Long
Querying for customer records where first_name = 'Josh':
Customer[id=3, firstName='Josh', lastName='Bloch']
Customer[id=4, firstName='Josh', lastName='Long']


*/