package br.com.cast.evaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.evaluation.payload.CoursePayload;
import br.com.cast.evaluation.response.CourseResponse;
import br.com.cast.evaluation.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cast")
@Api(value = "Cast")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@ApiOperation(value = "Incluir novos cursos")
	@PostMapping(path = "/save")
	public ResponseEntity<CourseResponse> saveCourse(@RequestBody CoursePayload payload){
		
		return new ResponseEntity<CourseResponse>(courseService.saveCourse(payload), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Alterar dados do Curso")
	@PutMapping(path = "/alter/{courseId}")
	public ResponseEntity<CourseResponse> updateCourse(@RequestBody CoursePayload payload, @PathVariable Long courseId){
		
		return new ResponseEntity<CourseResponse>(courseService.updateCourse(payload, courseId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Excluir um curso")
	@DeleteMapping(path = "/delete/{courseId}")
	public void deleteCourse(@PathVariable Long courseId){
		courseService.deleteCourse(courseId);
	}
	
	@ApiOperation(value = "Listar todos os cursos cadastrados")
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<CourseResponse>> getAllCourses(){
		return new ResponseEntity<List<CourseResponse>>(courseService.getAllCourses(), HttpStatus.OK);
	}

}
