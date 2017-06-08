package com.daniloaraujosilva.file_parser.model.bo;

import com.daniloaraujosilva.file_parser.model.enums.relatorio_eventos.EventoEnum;
import com.daniloaraujosilva.file_parser.model.enums.relatorio_eventos.HeaderEnum;
import com.daniloaraujosilva.file_parser.model.enums.relatorio_eventos.TipoEventoEnum;
import com.daniloaraujosilva.file_parser.model.exception.ClientCatchableException;
import com.daniloaraujosilva.file_parser.model.helper.MultiLevelTreeMap;
import com.daniloaraujosilva.file_parser.model.pojo.RelatorioEventosPojo;
import com.daniloaraujosilva.file_parser.model.utils.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Analisador de relatório de ocorrências.
 */
public class AnalisadorRelatorioEventosBO implements AnalisadorRelatorioEventosInterface {

	/**
	 *
	 */
	private File reportFile;

	/**
	 *
	 */
	private ArrayList<RelatorioEventosPojo> list;

	/**
	 *
	 */
	private MultiLevelTreeMap statistics;

	/**
	 *
	 */
	public AnalisadorRelatorioEventosBO() {
		this("");
	}

	/**
	 *
	 * @param reportFilePath
	 */
	public AnalisadorRelatorioEventosBO(String reportFilePath) {
		this(new File(reportFilePath));
	}

	/**
	 *
	 * @param reportFile
	 */
	public AnalisadorRelatorioEventosBO(File reportFile) {
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
	public ArrayList<RelatorioEventosPojo> getList() {
		return list;
	}

	/**
	 *
	 * @param list
	 */
	public void setList(ArrayList<RelatorioEventosPojo> list) {
		this.list = list;
	}

	/**
	 *
	 * @return
	 */
	public MultiLevelTreeMap getStatistics() {
		return statistics;
	}

	/**
	 *
	 * @param statistics
	 */
	public void setStatistics(MultiLevelTreeMap statistics) {
		this.statistics = statistics;
	}

	/**
	 *
	 * @throws ClientCatchableException
	 * @throws IOException
	 */
	public void parse() throws ClientCatchableException, IOException {
		prepare();

		Reader in = new FileReader(getReportFile().getAbsolutePath());

		try {
			list = new ArrayList<RelatorioEventosPojo>();

			Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
			for (CSVRecord record : records) {
				RelatorioEventosPojo item = new RelatorioEventosPojo();

				/*
					The validation and sanitization were omited.
					A critical application should take care about this, but to accomplish this example these
					strategies were omitted.
				 */
				item.setCodigoSequencial(NumberUtils.parseInteger(record.get(HeaderEnum.CODIGO_SEQUENCIAL.getId())));
				item.setCodigoCliente(StringUtils.trim(record.get(HeaderEnum.CODIGO_CLIENTE.getId())));
				item.setEvento(EventoEnum.getById(StringUtils.trim(record.get(HeaderEnum.CODIGO_EVENTO.getId()))));
				item.setTipoEvento(TipoEventoEnum.getById(StringUtils.trim(record.get(HeaderEnum.TIPO_EVENTO.getId()))));
				item.setDataInicio(DateTimeUtils.getDateTimeFromString(StringUtils.trim(record.get(HeaderEnum.DATA_INICIO.getId()))));
				item.setDataTermino(DateTimeUtils.getDateTimeFromString(StringUtils.trim(record.get(HeaderEnum.DATA_TERMINO.getId()))));
				item.setCodigoAtendente(StringUtils.trim(record.get(HeaderEnum.CODIGO_ATENDENTE.getId())));

				list.add(item);
			}

			generateStatistics();
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

	public void generateStatistics() {
		if (list == null) {
			throw new IllegalArgumentException("A lista de itens é inválida.");
		} else if (CollectionUtils.isEmpty(list)) {
			throw new IllegalArgumentException("A lista de itens está vazia.");
		}

		statistics = new MultiLevelTreeMap();

		HashSet<String> eventosIds = new HashSet<String>();
		statistics.put("eventos.ids", eventosIds);

		HashSet<String> clientesIds = new HashSet<String>();
		statistics.put("clientes.ids", clientesIds);

		HashSet<String> atendentesIds = new HashSet<String>();
		statistics.put("atendentes.ids", atendentesIds);

		ArrayList<Integer> idsEventosCriticos = new ArrayList<Integer>();

		for (RelatorioEventosPojo item : list) {
			String tiposEventosQuantidadesPath = String.format("eventos.tipos.quantidades.sequencial.%s", item.getTipoEvento().getId());
			String clientesQuantidadesPath = String.format("clientes.quantidades.sequencial.%s", item.getCodigoCliente());
			String atendentesQuantidadesAtendimentosPath = String.format("atendendentes.quantidadeAtendimentos.%s", item.getCodigoAtendente());
			String atendentesSomaAtendimentosPath = String.format("atendentes.somaAtendimentos.%s", item.getCodigoAtendente());
			String eventosCriticosPath = String.format("eventos.criticos.%s", item.getCodigoCliente());

			eventosIds.add(item.getEvento().getId());
			clientesIds.add(item.getCodigoCliente());
			atendentesIds.add(item.getCodigoAtendente());

			TipoEventoEnum ultimo = statistics.get(eventosCriticosPath + ".ultimo", TipoEventoEnum.class);

			if (
				ultimo != null
				&& TipoEventoEnum.ALARME.equals(ultimo)
				&& TipoEventoEnum.DESARME.equals(item.getTipoEvento())
			) {
				idsEventosCriticos.add(item.getCodigoSequencial());
			}

			statistics.put(eventosCriticosPath + ".ultimo", item.getTipoEvento());

			statistics.put(
				tiposEventosQuantidadesPath,
				ObjectUtils.coalesce(
					statistics.getAsInteger(tiposEventosQuantidadesPath),
					0,
					Integer.class
				) + 1
			);

			statistics.put(
				clientesQuantidadesPath,
				ObjectUtils.coalesce(
					statistics.getAsInteger(clientesQuantidadesPath),
					0,
					Integer.class
				) + 1
			);

			statistics.put(
				atendentesQuantidadesAtendimentosPath,
				ObjectUtils.coalesce(
					statistics.getAsInteger(atendentesQuantidadesAtendimentosPath),
					0,
					Integer.class
				) + 1
			);

			statistics.put(
				atendentesSomaAtendimentosPath,
				ObjectUtils.coalesce(
					statistics.getAsLong(atendentesSomaAtendimentosPath),
					0l,
					Long.class
				) + (
					Duration.between(
						item.getDataInicio(),
						item.getDataTermino()
					).getSeconds()
				)
			);
		}

		statistics.put("eventos.criticos.codigosSequenciais", idsEventosCriticos);

		for (String atendenteId : atendentesIds) {
			String quantidadesAtendimentosPath = String.format("atendendentes.quantidadeAtendimentos.%s", atendenteId);
			String somaAtendimentosPath = String.format("atendentes.somaAtendimentos.%s", atendenteId);
			String tempoMedioAtendimentosPath = String.format("atendentes.tempoMedioAtendimentos.%s", atendenteId);

			statistics.put(
				tempoMedioAtendimentosPath,
				statistics.getAsLong(somaAtendimentosPath)/statistics.getAsInteger(quantidadesAtendimentosPath)
			);
		}

		ArrayList<TipoEventoEnum> tiposEventosMaisOcorrencias = new ArrayList<TipoEventoEnum>();

		for (
			Object tipoEventoIdObject :
			MapUtils.sortByValueDescendent(statistics.get("eventos.tipos.quantidades.sequencial", Map.class)).keySet()
		) {
			tiposEventosMaisOcorrencias.add(TipoEventoEnum.getById(tipoEventoIdObject.toString()));
		}

		statistics.put(
			"eventos.tipos.quantidades.ordenadoDescendente",
			tiposEventosMaisOcorrencias
		);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Map<String, Integer> getTotalEventosCliente() {
		return statistics.get("clientes.quantidades.sequencial", Map.class);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Map<String, Long> getTempoMedioAtendimentoAtendente() {
		return statistics.get("atendentes.tempoMedioAtendimentos", Map.class);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<TipoEventoEnum> getTiposOrdenadosNumerosEventosDecrescente() {
		return statistics.get(	"eventos.tipos.quantidades.ordenadoDescendente", List.class);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<Integer> getCodigoSequencialEventosDesarmeAposAlarme() {
		return statistics.get("eventos.criticos.codigosSequenciais", List.class);
	}
}
