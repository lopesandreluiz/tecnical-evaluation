package br.com.cast.evalution.enums;

public enum CategoryEnum {
	
	BEHAVIORAL("Comportamental"),
	PROGRAMMING("Programação"),
	QUALITY("Qualidade"),
	LAW_SUIT("Processos");
	
	private final String description;
	
	CategoryEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
