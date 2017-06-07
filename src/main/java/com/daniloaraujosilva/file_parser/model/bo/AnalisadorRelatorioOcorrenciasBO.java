package com.daniloaraujosilva.file_parser.model.bo;

import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.EventoEnum;
import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.HeaderEnum;
import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.TipoEventoEnum;
import com.daniloaraujosilva.file_parser.model.exception.ClientCatchableException;
import com.daniloaraujosilva.file_parser.model.pojo.RelatorioOcorrenciasPojo;
import com.daniloaraujosilva.file_parser.model.utils.DateTimeUtils;
import com.daniloaraujosilva.file_parser.model.utils.NumberUtils;
import com.daniloaraujosilva.file_parser.model.utils.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Analisador de relatório de ocorrências.
 */
public class AnalisadorRelatorioOcorrenciasBO implements AnalisadorRelatorioOcorrenciasInterface {

	/**
	 *
	 */
	private File reportFile;

	/**
	 *
	 */
	private ArrayList<String> header;

	/**
	 *
	 */
	public AnalisadorRelatorioOcorrenciasBO() {
		this("");
	}

	/**
	 *
	 * @param reportFilePath
	 */
	public AnalisadorRelatorioOcorrenciasBO(String reportFilePath) {
		this(new File(reportFilePath));
	}

	/**
	 *
	 * @param reportFile
	 */
	public AnalisadorRelatorioOcorrenciasBO(File reportFile) {
		this.reportFile = reportFile;
	}

	/**
	 *
	 * @return
	 */
	public File getReportFile() {
		return reportFile;
	}

	/**
	 *
	 * @param reportFile
	 */
	public void setReportFile(File reportFile) {
		this.reportFile = reportFile;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<String> getHeader() {
		return header;
	}

	/**
	 *
	 * @param header
	 */
	public void setHeader(ArrayList<String> header) {
		this.header = header;
	}

	/**
	 *
	 * @return
	 */
	public Boolean parse() throws ClientCatchableException, IOException {
		prepare();

		Reader in = new FileReader(getReportFile().getAbsolutePath());

		try {
			ArrayList<RelatorioOcorrenciasPojo> list = new ArrayList<RelatorioOcorrenciasPojo>();

			Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
			for (CSVRecord record : records) {
				RelatorioOcorrenciasPojo item = new RelatorioOcorrenciasPojo();
				item.setCodigoSequencial(NumberUtils.parseInteger(record.get(HeaderEnum.CODIGO_SEQUENCIAL.getId())));
				item.setCodigoCliente(StringUtils.trim(record.get(HeaderEnum.CODIGO_CLIENTE.getId())));
				item.setEvento(EventoEnum.getById(StringUtils.trim(record.get(HeaderEnum.CODIGO_EVENTO.getId()))));
				item.setTipoEvento(TipoEventoEnum.getById(StringUtils.trim(record.get(HeaderEnum.TIPO_EVENTO.getId()))));
				item.setDataInicio(DateTimeUtils.getFromString(StringUtils.trim(record.get(HeaderEnum.DATA_INICIO.getId()))));
				item.setDataTermino(DateTimeUtils.getFromString(StringUtils.trim(record.get(HeaderEnum.DATA_TERMINO.getId()))));
				item.setCodigoAtendente(StringUtils.trim(record.get(HeaderEnum.CODIGO_ATENDENTE.getId())));

				list.add(item);
			}

			return true;
		} catch (Exception exception) {
			throw exception;
		} finally {
			in.close();
		}
	}

	/**
	 *
	 * @return
	 * @throws ClientCatchableException
	 */
	public Boolean prepare() throws ClientCatchableException {
		sanitize();
		validate();

		return true;
	}

	/**
	 *
	 * @return
	 * @throws ClientCatchableException
	 */
	public Boolean validate() throws ClientCatchableException {
		if (reportFile == null) {
			throw new ClientCatchableException("É necessário informar o relatório a ser processado.");
		} else if (!reportFile.exists()) {
			throw new ClientCatchableException(
				"O relatório não pode ser encontrado dado o caminho especificado. Caminho: \""
				+ reportFile.getAbsolutePath()
				+ "\"."
			);
		}

		return true;
	}

	/**
	 *
	 * @return
	 */
	public Boolean sanitize() {
		return true;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Map<String, Integer> getTotalEventosCliente() {
		return null;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Map<String, Long> getTempoMedioAtendimentoAtendente() {
		return null;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<TipoEventoEnum> getTiposOrdenadosNumerosEventosDecrescente() {
		return null;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<Integer> getCodigoSequencialEventosDesarmeAposAlarme() {
		return null;
	}
}
