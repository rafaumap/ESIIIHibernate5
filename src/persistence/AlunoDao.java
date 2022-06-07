package persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Aluno;

public class AlunoDao implements IDao<Aluno> {

  private SessionFactory sf;

  public AlunoDao(SessionFactory sf) {
    this.sf = sf;
  }

  @Override
  public void insere(Aluno al) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(al);
    transaction.commit();
  }

  @Override
  public void modifica(Aluno al) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.merge(al);
    transaction.commit();
  }

  @Override
  public void remove(Aluno al) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.remove(al);
    transaction.commit();
  }

  @Override
  public Aluno busca(Aluno al) {
    EntityManager entityManager = sf.createEntityManager();
    al = entityManager.find(Aluno.class, al.getRa());
    return al;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Aluno> lista() {
    List<Aluno> alunos = new ArrayList<Aluno>();
    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT a.* ");
    buffer.append("FROM aluno a ");
    buffer.append("ORDER BY a.ra");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();
    for (Object[] obj : lista) {
      Aluno al = new Aluno();
      al.setRa(obj[0].toString());
      al.setEmail(obj[1].toString());
      al.setNome(obj[2].toString());
      al.setPosicaoVestibular(Integer.parseInt(obj[3].toString()));

      alunos.add(al);
    }

    return alunos;
  }

}
