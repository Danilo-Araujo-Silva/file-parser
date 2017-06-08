package com.daniloaraujosilva.file_parser.model.service;

import com.daniloaraujosilva.file_parser.model.dao.RelatorioEventoDAO;
import com.daniloaraujosilva.file_parser.model.entity.RelatorioEventoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service for RelatorioEventoEntity.
 */
@Service
public class RelatorioEventoService extends AbstractEntityService<RelatorioEventoDAO, RelatorioEventoEntity> {

	/**
	 *
	 */
	@Autowired
	private RelatorioEventoDAO relatorioEventoDAO;
}
