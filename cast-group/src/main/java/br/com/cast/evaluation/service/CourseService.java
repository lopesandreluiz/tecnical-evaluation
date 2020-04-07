package br.com.cast.evaluation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.evaluation.exception.CategoryNotValidException;
import br.com.cast.evaluation.exception.CourseNotFoundException;
import br.com.cast.evaluation.exception.InitialDateInvalidException;
import br.com.cast.evaluation.exception.UsedPeriodException;
import br.com.cast.evaluation.model.Category;
import br.com.cast.evaluation.model.Course;
import br.com.cast.evaluation.payload.CoursePayload;
import br.com.cast.evaluation.repository.CourseRepository;
import br.com.cast.evaluation.response.CategoryResponse;
import br.com.cast.evaluation.response.CourseResponse;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	
	public CourseResponse saveCourse(CoursePayload payload) {
		
		categoryService.loadCategory();
		
		validationCourses(payload);
		
		return getCourseResponse(courseRepository.save(getCourse(payload)));
	}
	
	public CourseResponse updateCourse(CoursePayload payload, Long courseId) {
		
		validationCourses(payload);
		
		Course entity = getCourseById(courseId);
		payload.setId(entity.getId());
		return saveCourse(payload);		
	}
	
	public void deleteCourse(Long courseId) {
		
		 courseRepository.delete(getCourseById(courseId));
	}
	
	public List<CourseResponse> getAllCourses(){
		
		return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
				.map(this::getCourseResponse)
	    		.collect(Collectors.toList());
	}
	
	private void validationCourses(CoursePayload payload) {
		
		if(payload.getInitialDate().isBefore(LocalDate.now())) {
			throw new InitialDateInvalidException();
		}
		
		List<Course> listCourse = StreamSupport.stream(courseRepository.findAll().spliterator(), false)
				.filter(course -> (validInitialDate(course, payload) && validFinalDate(course, payload)))
				.collect(Collectors.toList());
					
		if(!listCourse.isEmpty()) {
			throw new UsedPeriodException();
		}
	}
	
	private Boolean validInitialDate(Course course, CoursePayload payload) {
		
		return ((payload.getInitialDate().equals(course.getInitialDate()) || payload.getInitialDate().isAfter(course.getInitialDate())) 
					&& (payload.getInitialDate().equals(course.getFinalDate()) || payload.getInitialDate().isBefore(course.getFinalDate())));
		
	}
	
	private Boolean validFinalDate(Course course, CoursePayload payload) {
		
		return ((payload.getFinalDate().equals(course.getInitialDate()) || payload.getFinalDate().isAfter(course.getInitialDate())) 
				&& (payload.getFinalDate().equals(course.getFinalDate()) || payload.getFinalDate().isBefore(course.getFinalDate())));
		
	}
	
	
	private Course getCourseById(Long courseId){
		
		Optional<Course> course = courseRepository.findById(courseId);
		
		if(course.isPresent()) {
			return course.get();
		}else {
			throw new CourseNotFoundException();
		}
	}
	
	
	private Course getCourse(CoursePayload payload) {
		
		Optional<Category> category = categoryService.getCategoryId(payload.getCategoryId());
		
		if(!category.isPresent()) {
			throw new CategoryNotValidException();
		}
		
		return Course.builder()
				  .id(payload.getId())
				  .descriptionSubject(payload.getDescriptionSubject())
				  .initialDate(payload.getInitialDate())
				  .finalDate(payload.getFinalDate())
				  .numberStudents(payload.getNumberStudents())
				  .category(category.get())
				  .build();
	}
	
	private CourseResponse getCourseResponse(Course course) {
		
		return CourseResponse.builder()
				  .id(course.getId())
				  .descriptionSubject(course.getDescriptionSubject())
				  .initialDate(course.getInitialDate())
				  .finalDate(course.getFinalDate())
				  .numberStudents(course.getNumberStudents())
				  .category(CategoryResponse.builder()
						  .id(course.getCategory().getId())
						  .description(course.getCategory().getDescription())
						  .build())
				  .build();
	}
}
