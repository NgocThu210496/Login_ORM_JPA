package com.ra.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan({"com.ra.controller", "com.ra.serviceImp", "com.ra.repositoryImp", "com.ra.mapper"})
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    //Cấu hình các thông tin kết nối đến DB
    //thông tin để kết nối với
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/JPA_Login_Demo");
        dataSource.setUsername("root");
        dataSource.setPassword("thuit123");
        return dataSource;
    }

    //Cấu hình thông số và cơ chế làm việc của JPA
    /*
     * hibernate.hbm2dll.auto: create, update, create-drop, none
     * create: Khi ứng dụng chạy, xóa các bảng trong CSDL và tạo các bảng mới
     * update: Khi ứng dụng chạy, so sánh các bảng trong CSDL và các entity, nếu có sự khác biệt sẽ cập nhật bổ sung
     * create-drop: xóa các bảng trong CSDL, tạo bảng mới, kết thúc chạy xóa các bảng CSDL
     * none
     * */
    public Properties addionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true"); //vì JPA nó tự tạo ra các câu truy vấn để làm việc với CSDL, mình muốn  nó show ra để mình thấy thôi !
        return properties;
    }

    //Cấu hình cho EntityManagerFactory
    //được sử dụng để tạo và quản lý các EntityManager (được sử dụng để thực hiện các thao tác với cơ sở dữ liệu).
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.ra.model"); //chỉ định các gói cần được quét để tìm kiếm các entity.
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(addionalProperties());
        return entityManagerFactory;
    }

    //@Quanlifier === @Autowird
    @Bean
    @Qualifier(value = "entityManager")
    //Nó làm nhiệm vụ quản lý các đối tượng Entity và từ đó chúng ta có thể sử dụng Entity Manager
    //để thực hiện các hoạt động CRUD (Create, Read, Update, Delete) trên cơ sở dữ liệu
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    //Cấu hình quản lý transaction: khi them, sua, xoa nó có thể gây lỗi or k thành công->ảnh hưởng đến DB, nên quản lý nó
    //Transaction là một tập hợp các hoạt động ghi và đọc dữ liệu liên quan
    // mà thường được thực hiện cùng nhau và được xem như là một thao tác duy nhất
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    //Cấu hình tài nguyên css, js nằm trong thư mục resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources/**")
                .addResourceLocations("/resources/");
    }

}
