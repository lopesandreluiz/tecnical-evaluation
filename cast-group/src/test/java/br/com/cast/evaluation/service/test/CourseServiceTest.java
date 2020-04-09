package br.com.cast.evaluation.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cast.evaluation.exception.CourseNotFoundException;
import br.com.cast.evaluation.exception.InitialDateInvalidException;
import br.com.cast.evaluation.mothes.CategoryMother;
import br.com.cast.evaluation.mothes.CourseMother;
import br.com.cast.evaluation.payload.CoursePayload;
import br.com.cast.evaluation.repository.CourseRepository;
import br.com.cast.evaluation.response.CourseResponse;
import br.com.cast.evaluation.service.CategoryService;
import br.com.cast.evaluation.service.CourseService;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

	@InjectMocks
	private CourseService service;

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private CategoryService categoryService;

	private CourseMother courseMother = new CourseMother();

	private CategoryMother categoryMother = new CategoryMother();

	@Test(expected = InitialDateInvalidException.class)
	public void saveCourseInitialDateInvalidTest() {

		CoursePayload coursePayload = courseMother.getCoursePayload();
		coursePayload.setInitialDate(LocalDate.now().minusDays(1));

		CourseResponse response = service.saveCourse(coursePayload);

		assertNotNull(response);
		assertEquals(response.getDescriptionSubject(), courseMother.getCoursePayload().getDescriptionSubject());

		verify(courseRepository).save(courseMother.getCourse());
	}

	@Test
	public void saveCourseTest() {

		when(categoryService.getCategoryId(1l)).thenReturn(categoryMother.getCategoryResponse());
		
		when(courseRepository.save(courseMother.getCoursePersistence())).thenReturn(courseMother.getCourse());

		CourseResponse response = service.saveCourse(courseMother.getCoursePayload());

		assertNotNull(response);
		assertEquals(response.getDescriptionSubject(), courseMother.getCoursePayload().getDescriptionSubject());

		verify(courseRepository).save(courseMother.getCoursePersistence());
	}
	
	@Test(expected = CourseNotFoundException.class)
	public void updateCourseNotFoundTest() {
		
		when(courseRepository.findById(1L)).thenReturn(Optional.empty());

		CourseResponse response = service.updateCourse(courseMother.getCoursePayload(), 1L);

		assertNotNull(response);
		assertEquals(response.getDescriptionSubject(), courseMother.getCoursePayload().getDescriptionSubject());

		verify(courseRepository).save(courseMother.getCoursePersistence());
	}
	
	@Test
	public void updateCourseTest() {

		when(categoryService.getCategoryId(1l)).thenReturn(categoryMother.getCategoryResponse());
		
		when(courseRepository.findById(1L)).thenReturn(Optional.of(courseMother.getCoursePersistence()));
		
		when(courseRepository.save(courseMother.getCoursePersistence())).thenReturn(courseMother.getCourse());

		CourseResponse response = service.updateCourse(courseMother.getCoursePayload(), 1L);

		assertNotNull(response);
		assertEquals(response.getDescriptionSubject(), courseMother.getCoursePayload().getDescriptionSubject());

		verify(courseRepository).findById(1L);
		verify(courseRepository).save(courseMother.getCoursePersistence());
	}
	
	@Test
	public void deleteCourseTest() {
		
		when(courseRepository.findById(1L)).thenReturn(Optional.of(courseMother.getCourse()));

		service.deleteCourse(1L);

		verify(courseRepository).delete(courseMother.getCourse());
		verify(courseRepository).findById(1L);
	}
	
	@Test
	public void getAllCoursesTest() {
			
		service.getAllCourses();

		verify(courseRepository).findAll();
	}


}
