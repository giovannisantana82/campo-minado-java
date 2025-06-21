package br.com.giovanni.cm;

import br.com.giovanni.cm.modelo.Tabuleiro;
import br.com.giovanni.cm.visao.TabuleiroConsole;

public class Aplicacao {

    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6,3);
        new TabuleiroConsole(tabuleiro);
    }
}
