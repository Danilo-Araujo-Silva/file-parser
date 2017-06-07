package com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias;

public enum TipoEventoEnum {

    ALARME("ALARME", "Alarme", "Alarme."),
    ARME("ARME", "Arme", "Arme."),
    DESARME("DESARME", "Desarme", "Desarme."),
    TESTE("TESTE", "Teste", "Teste."),
    ;

	/**
	 *
	 */
	private String id;

	/**
	 *
	 */
	private String title;

	/**
	 *
	 */
	private String description;

	/**
	 *
	 */
	private TipoEventoEnum(String id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	/**
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public static TipoEventoEnum getById(String id) {
		if (id == null) {
			return null;
		}

		for (TipoEventoEnum item : TipoEventoEnum.values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;
	}
}
