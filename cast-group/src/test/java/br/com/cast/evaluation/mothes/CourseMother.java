package br.com.cast.evaluation.mothes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import br.com.cast.evaluation.model.Category;
import br.com.cast.evaluation.model.Course;
import br.com.cast.evaluation.payload.CoursePayload;
import br.com.cast.evaluation.response.CategoryResponse;
import br.com.cast.evaluation.response.CourseResponse;

public class CourseMother {

	public CourseResponse getCourseResponse() {

		return CourseResponse.builder().id(1L)
				.category(CategoryResponse.builder().id(1l).description("Teste").build())
				.descriptionSubject("Teste").initialDate(LocalDate.now()).finalDate(LocalDate.now()).numberStudents(2)
				.build();

	}

	public List<CourseResponse> getListCoursesResponse() {

		List<CourseResponse> list = new ArrayList<>();
		list.add(getCourseResponse());
		return list;

	}

	public CoursePayload getCoursePayload() {

		CoursePayload payload = new CoursePayload();
		payload.setDescriptionSubject("Teste");
		payload.setCategoryId(1L);
		payload.setInitialDate(LocalDate.now());
		payload.setFinalDate(LocalDate.now());
		payload.setNumberStudents(3);

		return payload;
	}

	public static final ObjectMapper makeMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new ParameterNamesModule());
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new JavaTimeModule());
		return mapper;
	}
	
	public Course getCourse() {

		return Course.builder().id(1L).descriptionSubject("Teste").initialDate(LocalDate.now())
				.finalDate(LocalDate.now()).category(Category.builder().id(1l).description("Teste").build())
				.numberStudents(3).build();

	}
	
	public Course getCoursePersistence() {

		return Course.builder().descriptionSubject("Teste").initialDate(LocalDate.now())
				.finalDate(LocalDate.now()).category(Category.builder().id(1l).description("teste").build())
				.numberStudents(3).build();

	}
	
	public List<Course> getListCourses() {

		List<Course> list = new ArrayList<>();
		list.add(getCourse());
		return list;

	}
	
	

}
