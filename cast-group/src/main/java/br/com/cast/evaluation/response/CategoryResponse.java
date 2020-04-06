package br.com.cast.evaluation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse {
	
	private Long id;
	
	private String description;

}
