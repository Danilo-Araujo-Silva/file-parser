package com.daniloaraujosilva.file_parser.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Task entity.
 */
@Entity
@Table(name = "relatorio_evento")
public class RelatorioEventoEntity extends AbstractEntity<RelatorioEventoEntity> {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
}
