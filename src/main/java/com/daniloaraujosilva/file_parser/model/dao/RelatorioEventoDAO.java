package com.daniloaraujosilva.file_parser.model.dao;

import com.daniloaraujosilva.file_parser.model.entity.RelatorioEventoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO for .
 */
@Transactional
@Repository
public class RelatorioEventoDAO extends AbstractEntityDAO<RelatorioEventoEntity> {
}
