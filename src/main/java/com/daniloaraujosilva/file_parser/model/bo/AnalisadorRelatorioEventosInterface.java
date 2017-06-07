package com.daniloaraujosilva.file_parser.model.bo;

import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.TipoEventoEnum;
import com.daniloaraujosilva.file_parser.model.exception.ClientCatchableException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface do analisador do relatório de ocorrências.
 *
 * @see AnalisadorRelatorioEventosBO
 */
public interface AnalisadorRelatorioEventosInterface {

	/**
	 *
	 * @throws ClientCatchableException
	 * @throws IOException
	 */
	public void parse() throws ClientCatchableException, IOException;

    /**
     * Total de eventos agrupados por cliente.<br/>
     * <ul>
     * <li>key: Codigo do cliente</li>
     * <li>value: Total de eventos</li>
     * </ul>
     *
     * @return Total de eventos agrupados por cliente.
     */
    Map<String, Integer> getTotalEventosCliente();

    /**
     * Tempo medio de atendimento por atendente.<br/>
     * O tempo de atendimento e definido por
     *
     * <pre>
     * Data Fim - Data Inicio
     * </pre>
     * <ul>
     * <li>key: Codigo do atendente</li>
     * <li>value: Tempo medio (em segundos)</li>
     * </ul>
     *
     * @return O tempo medio de atendimento por atendente, em segundos.
     */
    Map<String, Long> getTempoMedioAtendimentoAtendente();

    /**
     * Retorna uma lista de tipos, ordenado de forma decrescente pela quantidade de eventos.
     *
     * @return Lista de tipos.
     */
    List<TipoEventoEnum> getTiposOrdenadosNumerosEventosDecrescente();

    /**
     * Retorna o codigo sequencial de um evento de desarme que tenha ocorrido apos alarme.<br/>
     * Importante notar que este tipo de evento so pode ser considerado quando o desarme ocorrer em ate 5 minutos apos o alarme.<br/>
     * Caso tenha excedido este periodo, nao devera ser reportado.<br/>
     * O tempo a ser considerado e sempre com base na data/hora inicial dos eventos comparados.
     *
     * @return Lista de codigos sequenciais de eventos com desarme apos o alarme.
     */
    List<Integer> getCodigoSequencialEventosDesarmeAposAlarme();
}
