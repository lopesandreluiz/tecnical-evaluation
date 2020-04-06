package br.com.cast.evaluation.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cast.evaluation.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	

}
