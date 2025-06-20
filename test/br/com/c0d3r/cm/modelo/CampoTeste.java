package br.com.c0d3r.cm.modelo;

import br.com.c0d3r.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void InicarCampo(){
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda(){
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Direita(){
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1EmCima(){
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Embaixo(){
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2(){
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho(){
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeMinadoNaoMarcado(){
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1(){

        Campo campo11 = new Campo(1 ,1);

        Campo campo22 = new Campo(2 ,2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2(){

        Campo campo11 = new Campo(1 ,1);
        Campo campo12 = new Campo(1 ,2);
        campo12.minar();

        Campo campo22 = new Campo(2 ,2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeObjetivoAlcancado(){
        campo.minar();
        campo.alternarMarcacao();
        assertTrue(campo.objetivoAlcancado()); // Campo está minado e marcado
    }

    @Test
    void testeMinasNaVizinhaca(){

        Campo campo = new Campo(1, 1);

        Campo vizinho1 = new Campo(1, 2);
        Campo vizinho2 = new Campo(2, 1);
        Campo vizinho3 = new Campo(0, 1);

        vizinho1.minar();
        vizinho2.minar();

        campo.adicionarVizinho(vizinho1);
        campo.adicionarVizinho(vizinho2);
        campo.adicionarVizinho(vizinho3);

        assertEquals(2, campo.minasNaVizinhaca()); // 2 Vizinhos minados.
    }

    @Test
    void testeReinicio(){
        campo.abrir();
        campo.alternarMarcacao();
        campo.minar();
        campo.reiniciar();
        assertFalse(campo.isAberto());
        assertFalse(campo.isMinado());
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeMarcado(){
        campo.alternarMarcacao();
        assertEquals("x", campo.toString()); // Campo está marcado
    }

    @Test
    void testeMinado(){
        campo.minar();

        try {
            campo.abrir();
        } catch (Exception e) {

        }
            assertEquals("*", campo.toString()); // Campo está minado
    }

    @Test
    void testeAbertoComMinasVizinhas(){

        Campo campo = new Campo(1, 1);
        Campo vizinho1 = new Campo(1, 2);
        Campo vizinho2 = new Campo(2, 1);

        vizinho1.minar();
        vizinho2.minar();

        campo.adicionarVizinho(vizinho1);
        campo.adicionarVizinho(vizinho2);

        campo.abrir();

        assertEquals("2", campo.toString());
    }

    @Test
    void testeAberto(){
        campo.abrir();
        assertEquals(" ", campo.toString()); // Campo está aberto
    }

}
