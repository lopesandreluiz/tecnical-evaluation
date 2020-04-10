package br.com.cast.evaluation.controller.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.cast.evaluation.controller.CourseController;
import br.com.cast.evaluation.mothes.CourseMother;
import br.com.cast.evaluation.service.CategoryService;
import br.com.cast.evaluation.service.CourseService;

public class CourseControllerTest {

	@InjectMocks
	private CourseController courseController;

	@Autowired
	private MockMvc mvc;

	@Mock
	private CourseService service;

	@Mock
	private CategoryService categoryService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(courseController).build();
	}

	@Test
	public void saveCourseTest() throws Exception {

		String json = CourseMother.makeMapper().writeValueAsString(CourseMother.getCoursePayload());

		when(service.saveCourse(CourseMother.getCoursePayload())).thenReturn(CourseMother.getCourseResponse());

		mvc.perform(MockMvcRequestBuilders.post("/course/save").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		verify(service).saveCourse(CourseMother.getCoursePayload());

	}

	@SuppressWarnings("static-access")
	@Test
	public void alterCourseTest() throws Exception {

		Long courseId = 1L;

		String json = CourseMother.makeMapper().writeValueAsString(CourseMother.getCoursePayload());

		when(service.updateCourse(CourseMother.getCoursePayload(), courseId)).thenReturn(CourseMother.getCourseResponse());

		mvc.perform(MockMvcRequestBuilders.put("/course/alter/" + courseId).content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(service).updateCourse(CourseMother.getCoursePayload(), courseId);

	}

	@Test
	public void deleteCourseTest() throws Exception {

		Long courseId = 1L;

		mvc.perform(MockMvcRequestBuilders.delete("/course/delete/" + courseId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(service).deleteCourse(courseId);

	}

	@Test
	public void getCourseTest() throws Exception {

		when(service.getAllCourses()).thenReturn(CourseMother.getListCoursesResponse());

		mvc.perform(MockMvcRequestBuilders.get("/course/findAll").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
		verify(service).getAllCourses();

	}

	

}
