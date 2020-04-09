package br.com.cast.evaluation.payload;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CoursePayload {
	
	@ApiModelProperty(value = "Código identificador do curso")
	@JsonIgnore
	private Long id;
	
	@ApiModelProperty(value = "Descrição do assunto", required = true)
	private String descriptionSubject;
	
	@ApiModelProperty(value = "Data de inicio do curso", required = true)
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate initialDate;
	
	@ApiModelProperty(value = "Data de fim do curso", required = true)
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate finalDate;
	
	@ApiModelProperty(value = "Quantidade de alunos por turma")
	private Integer numberStudents;
	
	@ApiModelProperty(value = "Identificador da categoria", required = true)
	private Long categoryId;

}
