package com.daniloaraujosilva.file_parser.model.bo;

import com.daniloaraujosilva.file_parser.model.enums.relatorio_ocorrencias.TipoEventoEnum;
import com.daniloaraujosilva.file_parser.model.exception.ClientCatchableException;
import com.daniloaraujosilva.file_parser.model.router.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AnalisadorRelatorioEventosBOTest {

    private AnalisadorRelatorioEventosInterface analisador;

    @Before
    public void before() throws IOException, ClientCatchableException {
        File reportFile = Router.getInstance().get("/src/test/resources", "relatorio.csv");
        analisador = new AnalisadorRelatorioEventosBO(reportFile);
        analisador.parse();
    }

    @Test
    public void totalDeEventosDoCliente0001() {
        assertEquals(7, analisador.getTotalEventosCliente().get("0001"), 0);
    }

    @Test
    public void totalDeEventosDoCliente0003() {
        assertEquals(3, analisador.getTotalEventosCliente().get("0003"), 0);
    }

    @Test
    public void tempoMedioDeAtendimentoEmSegundosDoAtendenteAT01() {
        assertEquals(159, analisador.getTempoMedioAtendimentoAtendente().get("AT01"), 0);
    }

    @Test
    public void tempoMedioDeAtendimentoEmSegundosDoAtendenteAT02() {
        assertEquals(156, analisador.getTempoMedioAtendimentoAtendente().get("AT02"), 0);
    }

    @Test
    public void tipoComMaisEventos() {
        assertArrayEquals(new TipoEventoEnum[] { TipoEventoEnum.ALARME, TipoEventoEnum.DESARME, TipoEventoEnum.TESTE, TipoEventoEnum.ARME },
                analisador.getTiposOrdenadosNumerosEventosDecrescente().toArray(new TipoEventoEnum[TipoEventoEnum.values().length]));
    }

    @Test
    public void identificarEvento() {
        assertArrayEquals(new Integer[] { 7 }, analisador.getCodigoSequencialEventosDesarmeAposAlarme().toArray(new Integer[1]));
    }
}