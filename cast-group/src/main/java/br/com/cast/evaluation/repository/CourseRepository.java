package br.com.cast.evaluation.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cast.evaluation.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
	
	
}
