package com.cnframe;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;

import com.cnframe.filter.AccessFilter;

@EnableSidecar
@EnableDiscoveryClient
@SpringCloudApplication
public class AppframeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppframeGatewayApplication.class, args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}
