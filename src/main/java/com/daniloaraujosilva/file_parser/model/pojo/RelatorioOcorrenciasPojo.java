package com.daniloaraujosilva.file_parser.model.pojo;

import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.EventoEnum;
import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.TipoEventoEnum;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 */
public class RelatorioOcorrenciasPojo {

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
	private LocalDate dataInicio;

	/**
	 *
	 */
	private LocalDate dataTermino;

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
	public RelatorioOcorrenciasPojo(
		Integer codigoSequencial,
		String codigoCliente,
		EventoEnum evento,
		TipoEventoEnum tipoEvento,
		LocalDate dataInicio,
		LocalDate dataTermino,
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
	public RelatorioOcorrenciasPojo() {
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
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	/**
	 *
	 * @param dataInicio
	 */
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 *
	 * @return
	 */
	public LocalDate getDataTermino() {
		return dataTermino;
	}

	/**
	 *
	 * @param dataTermino
	 */
	public void setDataTermino(LocalDate dataTermino) {
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
