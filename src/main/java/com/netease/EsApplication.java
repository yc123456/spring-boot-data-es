package com.netease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.netease.repository")
@ImportResource(locations = { "classpath:elasticsearch.xml" })
public class EsApplication {

	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}
}
