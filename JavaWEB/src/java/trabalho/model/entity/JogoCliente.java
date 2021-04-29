package trabalho.model.entity;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Dependent
@Entity
@NamedQueries({
    @NamedQuery(name = "JogoCliente.listarUsuario", query = "select jc "
            + "from JogoCliente jc join jc.jogo j "
            + "where jc.usuario = ?1")
    ,
    @NamedQuery(name = "JogoCliente.consultar", query = "select jc from JogoCliente jc where jc.usuario = ?1 and jc.jogo = ?2")
    ,
    @NamedQuery(name = "JogoCliente.listarJogo", query = "select jc from JogoCliente jc where jc.jogo = ?1 order by jc.usuario")
})

public class JogoCliente extends AbstractEntity {

    private double nota;
    private boolean favorito;
    private String nomeJogo;
    private String plataformaJogo;

    @OneToOne
    @JoinColumn(name = "idJogo")
    private Jogo jogo;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public JogoCliente() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getPlataformaJogo() {
        return plataformaJogo;
    }

    public void setPlataformaJogo(String plataformaJogo) {
        this.plataformaJogo = plataformaJogo;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }
}
