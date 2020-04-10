package br.com.cast.evaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.evaluation.response.CategoryResponse;
import br.com.cast.evaluation.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
@Api(value = "Cast-Categorias")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "Realizar a carga inicial das categorias")
	@PostMapping(path = "/load")
	public ResponseEntity<Object> loadCategory() {

		categoryService.loadCategory();
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Listar todas os categorias cadastradas")
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<CategoryResponse>> getAllCourses() {
		return new ResponseEntity<List<CategoryResponse>>(categoryService.getAllCategories(), HttpStatus.OK);
	}

}
