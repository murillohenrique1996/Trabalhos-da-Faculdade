package agmjava;

import java.util.Random;

public class Program {

    public static void main(String[] args) {
        Random r = new Random();
        int rec = 10;
        int[] aval = new int[5];
        Gene[][] g = new Gene[5][5];

        // Preenche a primeira linha da matriz com numeros aleatorios
        for (int i = 0; i < g[0].length; i++) {
            Gene gAux = new Gene();
            gAux.setTempo(r.nextInt(15) + 1);
            gAux.setRecurso(r.nextInt(10) + 1);
            g[0][i] = gAux;
        }

        // Preenche as demais linhas da matriz gerando sequencias aleatorias
        // da primeira linha
        for (int i = 1; i < g.length; i++) {
            g[i] = embaralharArray(g[0]);
        }

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                System.out.printf("%d %d, ", g[i][j].getTempo(), g[i][j].getRecurso());
            }
            System.out.printf("     %d", aval[i]);
            System.out.println();
        }
        System.out.println();

        // Avalia linha por linha e armazena em 'aval'
        for (int i = 0; i < g.length; i++) {
            aval[i] = avaliaGene(g[i], rec);
        }

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                System.out.printf("%d %d, ", g[i][j].getTempo(), g[i][j].getRecurso());
            }
            System.out.printf("     %d", aval[i]);
            System.out.println();
        }

    }

    static Gene[] embaralharArray(Gene[] ar) {
        Gene[] arrayCopia = new Gene[ar.length];

        System.arraycopy(ar, 0, arrayCopia, 0, ar.length);

        Random rnd = new Random();
        for (int i = arrayCopia.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            Gene a = arrayCopia[index];
            arrayCopia[index] = arrayCopia[i];
            arrayCopia[i] = a;
        }

        return arrayCopia;
    }

    static int avaliaGene(Gene[] gene, int recurso) {
        int cont = 0;
        int aux = 0;
        int aux2 = 0;
        Gene[] gAux = new Gene[5];

        for (int i = 0; i < gAux.length; i++) {
            Gene gAux2 = new Gene();
            gAux2.setTempo(0);
            gAux2.setRecurso(0);
            gAux[i] = gAux2;
        }

        for (int i = 0; i < gene.length; i++) {
            if ((gene[i].getRecurso() + aux) <= recurso) {
                aux += gene[i].getRecurso();
                //System.out.printf(" %d ", aux);
                System.arraycopy(gene, i, gAux, i, 1);
            } else {

                /*for(int j = 0; j < gAux.length; j++){
                    if(gAux[j].getTempo() > 0){
                        aux2 = gAux[j].getTempo();
                        //System.out.printf("aux2 = %d \n", aux2);
                        break;
                    } 
                }*/
                
                for (int j = 0; j < gAux.length; j++) {
                    if ((gAux[j].getTempo() < aux2 && gAux[j].getTempo() > 0) || aux2 == 0) {
                        aux2 = gAux[j].getTempo();
                        //System.out.printf("aux2 = %d \n", aux2);
                    } else if (gAux.length -1 == j) break; 
                        else if (gAux[j+1].getTempo() == 0) break;

                }
                
                for (int j = 0; j < gAux.length; j++) {
                    if (gAux[j].getTempo() > 0) {
                        gAux[j].setTempo(gAux[j].getTempo() - aux2);
                    }
                    if (gAux[j].getTempo() <= 0) {
                        aux -= gAux[j].getRecurso();
                        gAux[j].setRecurso(0);
                    }
                }
                cont += aux2;
                aux2 = 0;
            }
        }

        return cont;
    }

}
