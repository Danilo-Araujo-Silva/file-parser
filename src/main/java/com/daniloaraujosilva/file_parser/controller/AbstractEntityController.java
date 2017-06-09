package com.daniloaraujosilva.file_parser.controller;

import com.daniloaraujosilva.file_parser.model.dao.EntityDAOInterface;
import com.daniloaraujosilva.file_parser.model.entity.EntityInterface;
import com.daniloaraujosilva.file_parser.model.service.EntityServiceInterface;

import java.lang.reflect.ParameterizedType;

/**
 * Abstract class that all entity controllers should inherit.
 * Entity controllers are controllers common to provide entity information.
 * This class have the intention to provide common properties, methods or behaviors
 * 	to controllers about entities.
 */
abstract public class AbstractEntityController
	<
		Service extends EntityServiceInterface,
		Entity extends EntityInterface
	> extends AbstractController
	implements EntityControllerInterface {

	/**
	 *
	 */
	private Class<Entity> entityClass;

	/**
	 *
	 */
	private Class<Service> serviceClass;

	/**
	 *
	 */
	public AbstractEntityController() {
		setEntityClass(
			(Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
		);

		setServiceClass(
			(Class<Service>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2]
		);
	}

	/**
	 *
	 * @return
	 */
	public Class<Entity> getEntityClass() {
		return entityClass;
	}

	/**
	 *
	 * @param entityClass
	 */
	public void setEntityClass(Class<Entity> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 *
	 * @return
	 */
	public Class<Service> getServiceClass() {
		return serviceClass;
	}

	/**
	 *
	 * @param serviceClass
	 */
	public void setServiceClass(Class<Service> serviceClass) {
		this.serviceClass = serviceClass;
	}
}
