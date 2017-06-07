package com.daniloaraujosilva.file_parser.model.bo;

import com.daniloaraujosilva.file_parser.model.enums.TipoOcorrenciaEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AnalisadorRelatorioBOTest {

    AnalisadorRelatorioBOInterface analisador;

    @Before
    public void before() throws IOException {
        // analisador = sua implementação
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
        assertArrayEquals(new TipoOcorrenciaEnum[] { TipoOcorrenciaEnum.ALARME, TipoOcorrenciaEnum.DESARME, TipoOcorrenciaEnum.TESTE, TipoOcorrenciaEnum.ARME },
                analisador.getTiposOrdenadosNumerosEventosDecrescente().toArray(new TipoOcorrenciaEnum[TipoOcorrenciaEnum.values().length]));
    }

    @Test
    public void identificarEvento() {
        assertArrayEquals(new Integer[] { 7 }, analisador.getCodigoSequencialEventosDesarmeAposAlarme().toArray(new Integer[1]));
    }
}