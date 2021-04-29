package trabalho.model.entity;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Dependent
@Entity
@NamedQuery(name = "Jogo.listar", query = "select j from Jogo j order by j.nome")
public class Jogo extends AbstractEntity {

    private String nome;
    private String plataforma;
    private String descricao;
    private double media;

    public Jogo() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}
