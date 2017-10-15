package com.cnframe.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

public class FileUploadConfig {
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize("1024KB"); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("2056KB");
        // Sets the directory location where files will be stored.
        factory.setLocation("E://photoroot//images");
        return factory.createMultipartConfig();
    }
}
