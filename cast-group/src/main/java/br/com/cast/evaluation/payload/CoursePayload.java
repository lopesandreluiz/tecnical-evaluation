package br.com.cast.evaluation.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursePayload {
	
	@ApiModelProperty(value = "Código identificador do curso")
	@JsonIgnore
	private Long id;
	
	@ApiModelProperty(value = "Descrição do assunto", required = true)
	private String descriptionSubject;
	
	@ApiModelProperty(value = "Data de inicio do curso", required = true)
	private LocalDate initialDate;
	
	@ApiModelProperty(value = "Data de fim do curso", required = true)
	private LocalDate finalDate;
	
	@ApiModelProperty(value = "Quantidade de alunos por turma")
	private Integer numberStudents;
	
	@ApiModelProperty(value = "Identificador da categoria", required = true)
	private Long categoryId;

}
