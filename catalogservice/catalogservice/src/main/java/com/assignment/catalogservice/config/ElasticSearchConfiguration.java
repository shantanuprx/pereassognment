package com.assignment.catalogservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.assignment.catalogservice.repository")
public class ElasticSearchConfiguration extends ElasticsearchConfiguration {

	@Value("${elasticsearch.node.url:localhost:9200}")
	private String elasticSearchUrl;

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder().connectedTo(elasticSearchUrl).build();
	}

}
