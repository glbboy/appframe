package com.cnframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.repository.BaseRepositoryFactoryBean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@RestController
@RefreshScope
public class ChargeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChargeServiceApplication.class, args);
	}
}
