package persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Disciplina;

public class DisciplinaDao implements IDao<Disciplina> {

  private SessionFactory sf;

  public DisciplinaDao(SessionFactory sf) {
    this.sf = sf;
  }

  @Override
  public void insere(Disciplina ds) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(ds);
    transaction.commit();
  }

  @Override
  public void modifica(Disciplina ds) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.merge(ds);
    transaction.commit();
  }

  @Override
  public void remove(Disciplina ds) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.remove(ds);
    transaction.commit();
  }

  @Override
  public Disciplina busca(Disciplina ds) {
    EntityManager entityManager = sf.createEntityManager();
    ds = entityManager.find(Disciplina.class, ds.getCodigoDisc());
    return ds;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Disciplina> lista() {
    List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT d.* ");
    buffer.append("FROM disciplina d ");
    buffer.append("ORDER BY d.codigo_disc");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();
    for (Object[] obj : lista) {
      Disciplina ds = new Disciplina();
      ds.setCodigoDisc(Integer.parseInt(obj[0].toString()));
      ds.setCargaHoraria(Integer.parseInt(obj[1].toString()));
      ds.setNomeDisc(obj[2].toString());

      disciplinas.add(ds);
    }

    return disciplinas;
  }

}
