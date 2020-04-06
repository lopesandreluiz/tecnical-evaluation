package br.com.cast.evaluation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.evaluation.model.Category;
import br.com.cast.evaluation.repository.CategoryRepository;
import br.com.cast.evalution.enums.CategoryEnum;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public void loadCategory() {
		
		if(categoryRepository.findAll().spliterator().estimateSize() == 0) {
		
			for (CategoryEnum category : CategoryEnum.values()) {
				
				categoryRepository.save(Category.builder()
						.id(category.getCode())
						.description(category.getDescription())
						.build());
			}
		}
	}
	
	public Optional<Category> getCategoryId(Long categoryId) {
		
		return categoryRepository.findById(categoryId);
		
	}
	

}
