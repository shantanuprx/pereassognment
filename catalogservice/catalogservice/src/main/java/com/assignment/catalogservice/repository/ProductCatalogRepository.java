package com.assignment.catalogservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.assignment.catalogservice.entity.ProductCatalog;

public interface ProductCatalogRepository extends ElasticsearchRepository<ProductCatalog, Long>{

	public Page<ProductCatalog> findByProductName(String name, Pageable pageable);
	
//	public Page<ProductCatalog> findByProductName(String name);

	@Query("{\"query\": { \"regexp\": { \"productName\": { \"value\": ?0 }}}}")
	public Page<ProductCatalog> findAllProductCatalogs(String query, Pageable pageable);
	
}
