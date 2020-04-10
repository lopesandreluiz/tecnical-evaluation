package br.com.cast.evaluation.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cast.evaluation.exception.CategoryNotValidException;
import br.com.cast.evaluation.model.Category;
import br.com.cast.evaluation.mothes.CategoryMother;
import br.com.cast.evaluation.repository.CategoryRepository;
import br.com.cast.evaluation.response.CategoryResponse;
import br.com.cast.evaluation.service.CategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	@InjectMocks
	private CategoryService service;

	@Mock
	private CategoryRepository categoryRepository;

	@Test
	public void loadCategoryTest() {

		when(categoryRepository.save(Category.builder().description("Comportamental").build()))
				.thenReturn(CategoryMother.getCategory());

		service.loadCategory();

		verify(categoryRepository).save(Category.builder().description("Comportamental").build());
	}

	@Test(expected = CategoryNotValidException.class)
	public void getCategoryIdNotValidTest() {

		when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

		service.getCategoryId(1L);

		verify(categoryRepository).findById(1L);
	}

	@Test
	public void getCategoryIdTest() {

		when(categoryRepository.findById(1L)).thenReturn(Optional.of(CategoryMother.getCategory()));

		CategoryResponse response = service.getCategoryId(1L);

		assertNotNull(response);
		assertEquals(response.getId(), 1L);

		verify(categoryRepository).findById(1L);
	}

}
