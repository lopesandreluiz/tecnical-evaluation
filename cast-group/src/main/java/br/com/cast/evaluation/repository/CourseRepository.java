package br.com.cast.evaluation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cast.evaluation.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
	
	List<Course> findByInitialDateGreaterThanEqualAndFinalDateLessThanEqual(LocalDate initialDate, LocalDate finalDate);
	

}
