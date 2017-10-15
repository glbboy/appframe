package com.cnframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cnframe.repository.BaseRepositoryFactoryBean;

@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class QuartzServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuartzServiceApplication.class, args);
	}
	
}
