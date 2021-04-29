package trabalho.model.entity;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import org.hibernate.validator.constraints.Email;

@Dependent
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.listar", query = "select u from Usuario u order by u.id")
    ,
    @NamedQuery(name = "Usuario.autenticar", query = "select u from Usuario u where u.email = ?1 and u.senha = ?2")
    ,
    @NamedQuery(name = "Usuario.existente", query = "select u from Usuario u where u.email like ?1 order by u.nome")
})
public class Usuario extends AbstractEntity {

    private String nome;
    @Email
    private String email;
    private String senha;
    private int tipoUsuario;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
