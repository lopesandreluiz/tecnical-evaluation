package br.com.cast.evaluation.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String descriptionSubject;
	
	@NotNull
	private LocalDate initialDate;
	
	@NotNull
	private LocalDate finalDate;
	
	private Integer numberStudents;
	
	@NotNull
	@ManyToOne
	@JoinColumn
	private Category category;

}
