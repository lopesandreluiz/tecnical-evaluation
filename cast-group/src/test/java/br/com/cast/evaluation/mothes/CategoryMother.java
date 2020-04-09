package br.com.cast.evaluation.mothes;

import br.com.cast.evaluation.model.Category;
import br.com.cast.evaluation.response.CategoryResponse;

public class CategoryMother {

	public CategoryResponse getCategoryResponse() {

		return CategoryResponse.builder().id(1L)
				.description("teste").build();

	}
	
	public Category getCategory() {

		return Category.builder().id(1L)
				.description("teste").build();

	}

	
	
	

}
