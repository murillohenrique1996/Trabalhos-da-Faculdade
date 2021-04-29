/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agmjava;

/**
 *
 * @author Vinicius
 */
public class Gene {
    private int tempo;
    private int recurso;

    public Gene(int tempo, int recurso) {
        this.tempo = tempo;
        this.recurso = recurso;
    }

    public Gene() {
    } 
    
    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }
    
    
}
