package com.daniloaraujosilva.file_parser.model.pojo;

import com.daniloaraujosilva.file_parser.model.enums.relatorio_eventos.EventoEnum;
import com.daniloaraujosilva.file_parser.model.enums.relatorio_eventos.TipoEventoEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 */
public class RelatorioEventosPojo {

	/**
	 *
	 */
	private Integer codigoSequencial;

	/**
	 *
	 */
	private String codigoCliente;

	/**
	 *
	 */
	private EventoEnum evento;

	/**
	 *
	 */
	private TipoEventoEnum tipoEvento;

	/**
	 *
	 */
	private LocalDateTime dataInicio;

	/**
	 *
	 */
	private LocalDateTime dataTermino;

	/**
	 *
	 */
	private String codigoAtendente;

	/**
	 *
	 * @param codigoSequencial
	 * @param codigoCliente
	 * @param evento
	 * @param tipoEvento
	 * @param dataInicio
	 * @param dataTermino
	 * @param codigoAtendente
	 */
	public RelatorioEventosPojo(
		Integer codigoSequencial,
		String codigoCliente,
		EventoEnum evento,
		TipoEventoEnum tipoEvento,
		LocalDateTime dataInicio,
		LocalDateTime dataTermino,
		String codigoAtendente
	) {
		this.codigoSequencial = codigoSequencial;
		this.codigoCliente = codigoCliente;
		this.evento = evento;
		this.tipoEvento = tipoEvento;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.codigoAtendente = codigoAtendente;
	}

	/**
	 *
	 */
	public RelatorioEventosPojo() {
	}

	/**
	 *
	 * @return
	 */
	public Integer getCodigoSequencial() {
		return codigoSequencial;
	}

	/**
	 *
	 * @param codigoSequencial
	 */
	public void setCodigoSequencial(Integer codigoSequencial) {
		this.codigoSequencial = codigoSequencial;
	}

	/**
	 *
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 *
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 *
	 * @return
	 */
	public EventoEnum getEvento() {
		return evento;
	}

	/**
	 *
	 * @param evento
	 */
	public void setEvento(EventoEnum evento) {
		this.evento = evento;
	}

	/**
	 *
	 * @return
	 */
	public TipoEventoEnum getTipoEvento() {
		return tipoEvento;
	}

	/**
	 *
	 * @param tipoEvento
	 */
	public void setTipoEvento(TipoEventoEnum tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	/**
	 *
	 * @param dataInicio
	 */
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	/**
	 *
	 * @param dataTermino
	 */
	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	/**
	 *
	 * @return
	 */
	public String getCodigoAtendente() {
		return codigoAtendente;
	}

	/**
	 *
	 * @param codigoAtendente
	 */
	public void setCodigoAtendente(String codigoAtendente) {
		this.codigoAtendente = codigoAtendente;
	}
}
