package com.daniloaraujosilva.file_parser.model.service;

import com.daniloaraujosilva.file_parser.model.dao.RelatorioEventoDAO;
import com.daniloaraujosilva.file_parser.model.entity.RelatorioEventoEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class RelatorioEventoService extends AbstractEntityService<RelatorioEventoDAO, RelatorioEventoEntity> {

	/**
	 *
	 */
	@Autowired
	private RelatorioEventoService relatorioEventoService;
}
