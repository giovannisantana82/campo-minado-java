    package br.com.c0d3r.cm.modelo;

    import br.com.c0d3r.cm.excecao.ExplosaoException;

    import java.util.ArrayList;
    import java.util.List;

    public class Campo {

        private final int linha;
        private final int coluna;

        private boolean aberto = false;
        private boolean minado = false;
        private boolean marcado = false;

        private List<Campo> vizinhos = new ArrayList<>();

        public Campo(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

         boolean adicionarVizinho(Campo vizinho){
            boolean linhaDiferente = linha != vizinho.linha;
            boolean colunaDiferente = coluna != vizinho.coluna;
            boolean diagonal = linhaDiferente && colunaDiferente;

            int deltaLinha = Math.abs(linha - vizinho.linha);
            int deltaColuna = Math.abs(coluna - vizinho.coluna);
            int deltaGeral = deltaColuna + deltaLinha;

            if (deltaGeral == 1 && !diagonal){
                vizinhos.add(vizinho);
                return true;
            } else if (deltaGeral == 2 && diagonal){
                vizinhos.add(vizinho);
                return true;
            } else{
                return false;
            }
        }

        void alternarMarcacao(){
            if (!aberto) {
                marcado = !marcado;
            }
        }

        boolean abrir(){

            if (!aberto && !marcado){
                aberto = true;

                if (minado) {
                    throw new ExplosaoException();
                }

                if (vizinhacaSegura()){
                    vizinhos.forEach(v -> v.abrir());
                }
                return true;
            } else {
            return false;
            }
        }

        boolean vizinhacaSegura () {
            return vizinhos.stream().noneMatch(v -> v.minado);
        }

        void minar() {
            minado = true;
            }

        public boolean isMarcado() {
            return marcado;
        }

         void setAberto(boolean aberto) {
            this.aberto = aberto;
        }

        public boolean isMinado(){
            return minado;
        }

        public boolean isAberto(){
            return aberto;
        }

        public boolean isFechado(){
            return !isAberto();
        }

        public int getLinha() {
            return linha;
        }

        public int getColuna() {
            return coluna;
        }

        boolean objetivoAlcancado() {
            boolean desvendado = !minado && aberto;
            boolean protegido = minado && marcado;
            return desvendado || protegido;
        }

        long minasNaVizinhaca() {
            return vizinhos.stream().filter(v -> v.minado).count();
        }

        void reiniciar() {
            aberto = false;
            minado = false;
            marcado = false;
        }

        @Override
        public String toString() {
            if(marcado) {
                return "x";
            } else if (aberto && minado) {
                return "*";
            } else if (aberto && minasNaVizinhaca() > 0) {
                return Long.toString(minasNaVizinhaca());
            } else if (aberto) {
                return " ";
            } else {
                return "?";
            }
        }
    }
