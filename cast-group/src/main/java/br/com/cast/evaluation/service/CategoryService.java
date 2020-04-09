package br.com.cast.evaluation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.evaluation.exception.CategoryNotValidException;
import br.com.cast.evaluation.model.Category;
import br.com.cast.evaluation.repository.CategoryRepository;
import br.com.cast.evaluation.response.CategoryResponse;
import br.com.cast.evalution.enums.CategoryEnum;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public void loadCategory() {

		for (CategoryEnum category : CategoryEnum.values()) {

			categoryRepository
					.save(Category.builder().description(category.getDescription()).build());
		}
	}

	public CategoryResponse getCategoryId(Long categoryId) {

		Optional<Category> category = categoryRepository.findById(categoryId);

		if (!category.isPresent()) {
			throw new CategoryNotValidException();
		}

		return CategoryResponse.builder().id(category.get().getId()).description(category.get().getDescription())
				.build();
	}

}
