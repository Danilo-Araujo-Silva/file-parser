package com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias;

/**
 *
 */
public enum HeaderEnum {

	CODIGO_SEQUENCIAL(0, "Código Sequencial", "Código sequencial."),
	CODIGO_CLIENTE(1, "Código do Cliente", "Código do cliente."),
	CODIGO_EVENTO(2, "Código do Evento", "Código do evento."),
	TIPO_EVENTO(3, "Tipo do Evento", "Tipo do evento."),
	DATA_INICIO(4, "Data de Início", "Data de início."),
	DATA_TERMINO(5, "Data de Término", "Data de término."),
	CODIGO_ATENDENTE(6, "Código do Atendente", "Código do atendente."),
	;

	/**
	 *
	 */
	private Integer id;

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
	 * @param id
	 * @param title
	 * @param description
	 */
	private HeaderEnum(Integer id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	/**
	 *
	 * @return
	 */
	public Integer getId() {
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
	 * @param index
	 * @return
	 */
	public static HeaderEnum getById(Integer index) {
		if (index == null) {
			return null;
		}

		for (HeaderEnum item : HeaderEnum.values()) {
			if (item.getId().equals(index)) {
				return item;
			}
		}

		return null;
	}
}
