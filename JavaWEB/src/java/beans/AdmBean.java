package beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.DAO;
import trabalho.model.entity.Jogo;
import trabalho.model.entity.Usuario;
import tx.Transacional;

@Named(value = "admBean")
@SessionScoped
public class AdmBean implements Serializable {

    @Inject
    private Usuario usuario;
    @Inject
    private Jogo jogo;
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Jogo> jogos = new ArrayList<>();
    
    @Inject
    private DAO<Usuario> dao_u;
    @Inject
    private DAO<Jogo> dao_j;
    
    public AdmBean() {
    }
    
    @PostConstruct
    public void init(){
        setAttributes();
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
    
    @Transacional
    public String deleteUser(Usuario u) throws SQLException, IOException {
        dao_u.excluir(u.getId());
        return atualizar();
    }
    
    @Transacional
    public String deleteJogo(Jogo j) throws SQLException, IOException {
        dao_j.excluir(j.getId());
        return atualizar();
    }
    
    @Transacional
    public String alterarJogo(Long idJogo) throws SQLException {
        jogo = dao_j .consultar(idJogo);
        return "/alterarJogo";
    }

    public String atualizar() throws SQLException {
        setAttributes();

        return "/telaADM";
    }
    
    @Transacional
    private void setAttributes() {
        usuarios = dao_u.listar("Usuario.listar");
        jogos = dao_j.listar("Jogo.listar");
    }
}
