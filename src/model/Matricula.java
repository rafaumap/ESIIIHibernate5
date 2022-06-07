package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "matriculas")
public class Matricula {
  @Id
  @ManyToOne
  @JoinColumn(name = "ra")
  @NotNull
  private Aluno aluno;

  @Id
  @ManyToOne
  @JoinColumn(name = "codigo_disc")
  @NotNull
  private Disciplina disciplina;

  @Column(name = "ano")
  @NotNull
  private int ano;

  @Column(name = "semestre")
  @NotNull
  private int semestre;

  public Aluno getAluno() {
    return this.aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Disciplina getDisciplina() {
    return this.disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  public int getAno() {
    return this.ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public int getSemestre() {
    return this.semestre;
  }

  public void setSemestre(int semestre) {
    this.semestre = semestre;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Matricula)) {
      return false;
    }
    Matricula matricula = (Matricula) o;
    return Objects.equals(aluno, matricula.aluno) && Objects.equals(disciplina, matricula.disciplina)
        && ano == matricula.ano && semestre == matricula.semestre;
  }

  @Override
  public int hashCode() {
    return Objects.hash(aluno, disciplina, ano, semestre);
  }

  @Override
  public String toString() {
    return "{" +
        " aluno='" + getAluno() + "'" +
        ", disciplina='" + getDisciplina() + "'" +
        ", ano='" + getAno() + "'" +
        ", semestre='" + getSemestre() + "'" +
        "}";
  }

}
