package com.daniloaraujosilva.file_parser.model.enums.relatorio_eventos;

/**
 *
 */
public enum EventoEnum {

	E130("E130", "Evento E130", "Evento E130."),
	E131("E131", "Evento E131", "Evento E131."),
	E132("E132", "Evento E132", "Evento E132."),
	E133("E133", "Evento E133", "Evento E133."),
	E134("E134", "Evento E134", "Evento E134."),
	E135("E135", "Evento E135", "Evento E135."),
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
	private EventoEnum(String id, String title, String description) {
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
	public static EventoEnum getById(String id) {
		if (id == null) {
			return null;
		}

		for (EventoEnum item : EventoEnum.values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;
	}
}
