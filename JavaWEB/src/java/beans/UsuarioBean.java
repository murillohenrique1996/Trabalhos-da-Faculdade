package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.DAO;
import trabalho.model.entity.Usuario;
import session.SessionContext;
import tx.Transacional;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @Inject
    private Usuario usuario;
    
    @Inject
    private DAO<Usuario> dao_u;

    public UsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Transacional
    public String autenticar() throws SQLException {
        Usuario _usuario = dao_u.buscar("Usuario.autenticar", usuario.getEmail(), usuario.getSenha());

        if (_usuario != null) {
            usuario = _usuario;
            SessionContext session = SessionContext.getInstance();
            session.setAttribute("usuario", usuario);
            if (usuario.getTipoUsuario() == 1) {
                return "/telaADM";
            } else {
                return "/telaPerfil";
            }
        }

        FacesMessage message = new FacesMessage("Login/senha inválidos!");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("form:entrar", message);
        return null;
    }

    @Transacional
    public String cadastrar() throws SQLException {
        Usuario _usuario = dao_u.buscar("Usuario.existente", usuario.getEmail());

        if (_usuario == null) {
            usuario.setTipoUsuario(0);
            dao_u.adicionar(usuario);

            SessionContext session = SessionContext.getInstance();
            session.setAttribute("usuario", usuario);

            return "/telaLogin";
        }

        FacesMessage message = new FacesMessage("Usuário já existente");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("form:entrar", message);
        return null;
    }

    public String sair() {
        SessionContext session = SessionContext.getInstance();
        session.encerrarSessao();
        return "/telaLogin";
    }
}
