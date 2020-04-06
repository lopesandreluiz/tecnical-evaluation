package br.com.cast.evalution.enums;

public enum CategoryEnum {
	
	BEHAVIORAL(1l,"Comportamental"),
	PROGRAMMING(2l,"Programação"),
	QUALITY(3l, "Qualidade"),
	LAW_SUIT(4l, "Processos");
	
	private final Long code;
	private final String description;
	
	CategoryEnum(Long code, String description) {
		this.code = code;
		this.description = description;
	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
