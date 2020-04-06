package br.com.cast.evaluation.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseResponse {
	
	private Long id;
	
	private String descriptionSubject;
	
	private LocalDate initialDate;
	
	private LocalDate finalDate;
	
	private Integer numberStudents;
	
	private CategoryResponse category;

}
