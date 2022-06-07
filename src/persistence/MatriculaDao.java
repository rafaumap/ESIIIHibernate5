package persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Aluno;
import model.Disciplina;
import model.Matricula;

public class MatriculaDao {
  private SessionFactory sf;

  public MatriculaDao(SessionFactory sf) {
    this.sf = sf;
  }

  public void insere(Matricula mat) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(mat);
    transaction.commit();
  }

  @SuppressWarnings("unchecked")
  public void remove(Matricula mat) {
    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT a.*, d.*, m.ano, m.semestre ");
    buffer.append("FROM matriculas m ");
    buffer.append("LEFT JOIN alunos a ON a.ra = m.ra");
    buffer.append("LEFT JOIN disciplinas d ON d.codigo_disc = m.codigo_disc ");
    buffer.append("WHERE m.ra = " + mat.getAluno().getRa());
    buffer.append(" AND m.codigo_disc = " + mat.getDisciplina().getCodigoDisc());
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());

    List<Object[]> lista = query.getResultList();
    for (Object[] obj : lista) {
      Aluno al = new Aluno();
      al.setRa(obj[0].toString());
      al.setEmail(obj[1].toString());
      al.setNome(obj[2].toString());
      al.setPosicaoVestibular(Integer.parseInt(obj[3].toString()));

      Disciplina ds = new Disciplina();
      ds.setCodigoDisc(Integer.parseInt(obj[4].toString()));
      ds.setCargaHoraria(Integer.parseInt(obj[5].toString()));
      ds.setNomeDisc(obj[6].toString());

      mat.setDisciplina(ds);
      mat.setAluno(al);
      mat.setAno(Integer.parseInt(obj[7].toString()));
      mat.setSemestre(Integer.parseInt(obj[8].toString()));
    }

    entityManager.remove(mat);
  }

  @SuppressWarnings("unchecked")
  public List<Matricula> lista() {
    List<Matricula> matriculas = new ArrayList<Matricula>();
    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT a.*, d.*, m.ano, m.semestre ");
    buffer.append("FROM matriculas m ");
    buffer.append("LEFT JOIN alunos a ON a.ra = m.ra");
    buffer.append("LEFT JOIN disciplinas d ON d.codigo_disc = m.codigo_disc ");
    buffer.append("ORDER BY m.ano");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();
    for (Object[] obj : lista) {
      Aluno al = new Aluno();
      al.setRa(obj[0].toString());
      al.setEmail(obj[1].toString());
      al.setNome(obj[2].toString());
      al.setPosicaoVestibular(Integer.parseInt(obj[3].toString()));

      Disciplina ds = new Disciplina();
      ds.setCodigoDisc(Integer.parseInt(obj[4].toString()));
      ds.setCargaHoraria(Integer.parseInt(obj[5].toString()));
      ds.setNomeDisc(obj[6].toString());

      Matricula mat = new Matricula();
      mat.setDisciplina(ds);
      mat.setAluno(al);
      mat.setAno(Integer.parseInt(obj[7].toString()));
      mat.setSemestre(Integer.parseInt(obj[8].toString()));

      matriculas.add(mat);
    }

    return matriculas;
  }
}
