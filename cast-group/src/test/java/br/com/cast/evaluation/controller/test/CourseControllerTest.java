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
	
	
	private CourseMother loadObjects = new CourseMother();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(courseController).build();
	}

	@SuppressWarnings("static-access")
	@Test
	public void saveCourseTest() throws Exception {

		String json = loadObjects.makeMapper().writeValueAsString(loadObjects.getCoursePayload());

		when(service.saveCourse(loadObjects.getCoursePayload())).thenReturn(loadObjects.getCourseResponse());

		mvc.perform(MockMvcRequestBuilders.post("/api/cast/save").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		verify(service).saveCourse(loadObjects.getCoursePayload());

	}

	@SuppressWarnings("static-access")
	@Test
	public void alterCourseTest() throws Exception {

		Long courseId = 1L;

		String json = loadObjects.makeMapper().writeValueAsString(loadObjects.getCoursePayload());

		when(service.updateCourse(loadObjects.getCoursePayload(), courseId)).thenReturn(loadObjects.getCourseResponse());

		mvc.perform(MockMvcRequestBuilders.put("/api/cast/alter/" + courseId).content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(service).updateCourse(loadObjects.getCoursePayload(), courseId);

	}

	@Test
	public void deleteCourseTest() throws Exception {

		Long courseId = 1L;

		mvc.perform(MockMvcRequestBuilders.delete("/api/cast/delete/" + courseId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(service).deleteCourse(courseId);

	}

	@Test
	public void getCourseTest() throws Exception {

		when(service.getAllCourses()).thenReturn(loadObjects.getListCoursesResponse());

		mvc.perform(MockMvcRequestBuilders.get("/api/cast/findAll").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
		verify(service).getAllCourses();

	}

	

}
