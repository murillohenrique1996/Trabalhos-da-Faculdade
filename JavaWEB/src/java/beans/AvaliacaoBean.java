package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.DAO;
import trabalho.model.entity.Jogo;
import trabalho.model.entity.JogoCliente;
import trabalho.model.entity.Usuario;
import session.SessionContext;
import tx.Transacional;

@Named(value = "avaliacaoBean")
@SessionScoped
public class AvaliacaoBean implements Serializable {

    private static final Map<String, Object> VALORESDOSATRIBUTOS;

    static {
        VALORESDOSATRIBUTOS = new HashMap<>();
        VALORESDOSATRIBUTOS.put("type", "number");
        VALORESDOSATRIBUTOS.put("min", "0");
        VALORESDOSATRIBUTOS.put("max", "5");
        VALORESDOSATRIBUTOS.put("required", "required");
        VALORESDOSATRIBUTOS.put("title",
                "Insira uma nota de 0 a 5");
    }

    @Inject
    private JogoCliente jogoCliente;
    @Inject
    private Jogo jogo;
    private List<Jogo> jogos = new ArrayList<>();
    private List<JogoCliente> jogosC = new ArrayList<>();
    
    @Inject
    private DAO<Jogo> dao_j;
    @Inject
    private DAO<JogoCliente> dao_jc;
    
    
    public AvaliacaoBean() {
    }
    
    @PostConstruct
    public void init(){
        setAttributes();
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public JogoCliente getJogoCliente() {
        return jogoCliente;
    }

    public void setJogoCliente(JogoCliente jogoCliente) {
        this.jogoCliente = jogoCliente;
    }

    public List<JogoCliente> getJogosC() throws SQLException {
        return jogosC;
    }

    public void setJogosC(List<JogoCliente> jogosC) {
        this.jogosC = jogosC;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Map<String, Object> getValoresDosAtributos() {
        return VALORESDOSATRIBUTOS;
    }

    @Transacional
    public String carregaTelaJogo(Long idJogo) throws SQLException {
        Usuario usuario = getIdUsuario();

        jogo = dao_j.consultar(idJogo);
        jogoCliente = dao_jc.buscar("JogoCliente.consultar", usuario, jogo);

        if (jogoCliente == null) {
            jogoCliente = new JogoCliente();
            jogoCliente.setUsuario(usuario);
            jogoCliente.setJogo(jogo);
            jogoCliente.setFavorito(false);
        }
        return "/telaJogo";
    }
    
    @Transacional
    public void alterarNota() throws SQLException {
        if (jogoCliente.getId() == null) {
            dao_jc.adicionar(jogoCliente);
        } else {
            dao_jc.alterar(jogoCliente);
        }

        calcularMedia();
    }

    public Usuario getIdUsuario() {
        SessionContext session = SessionContext.getInstance();
        Usuario _usuario = (Usuario) session.getAttribute("usuario");
        return _usuario;
    }
    
    @Transacional
    public String atualizar() throws SQLException {
        setAttributes();
        return "/telaUser";
    }

    private void calcularMedia() {
        double media = 0;

        List<JogoCliente> jcs = dao_jc.listar("JogoCliente.listarJogo", jogo);

        for (JogoCliente jc : dao_jc.listar("JogoCliente.listarJogo", jogo)) {
            media += jc.getNota();
        }
        media = media / jcs.size();

        jogo.setMedia(media);
        dao_j.alterar(jogo);
    }
    
    private void setAttributes() {
        jogosC = dao_jc.listar("JogoCliente.listarUsuario", getIdUsuario());
        jogos = dao_j.listar("Jogo.listar");
    }
}
