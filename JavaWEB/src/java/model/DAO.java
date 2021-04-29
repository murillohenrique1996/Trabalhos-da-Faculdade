package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.util.JPAEntityManager;

public class DAO<T> implements Serializable{

    private final Class<T> classe;
    private EntityManager manager;

    public DAO(EntityManager manager, Class<T> classe) {
        this.classe = classe;
        this.manager = manager;
    }

    public void adicionar(T t) {
        manager.persist(t);
    }

    public T consultar(Long id) {
        T instancia = manager.find(classe, id);
        return instancia;
    }

    public void alterar(T t) {
        manager.merge(t);
    }

    public void excluir(Long id) {
        T t = manager.find(classe, id);
        manager.remove(t);
    }

    public List<T> listar(String query, Object... params) {
        Query q = manager.createNamedQuery(query);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        List<T> lista = q.getResultList();
        return lista;
    }

    public T buscar(String query, Object... params) {
        Query q = manager.createNamedQuery(query);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        List<T> lista = q.getResultList();
        if (lista.size() > 0) {
            return lista.get(0);
        } else {
            return null;
        }
    }
}
