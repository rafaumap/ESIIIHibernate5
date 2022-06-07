package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "alunos")
public class Aluno {
  @Id
  @Column(name = "ra", length = 13)
  @NotNull
  private String ra;

  @Column(name = "nome", length = 60)
  @NotNull
  private String nome;

  @Column(name = "email", length = 40)
  @NotNull
  private String email;

  @Column(name = "posicao_vestibular")
  @NotNull
  private int posicaoVestibular;

  public String getRa() {
    return this.ra;
  }

  public void setRa(String ra) {
    this.ra = ra;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getPosicaoVestibular() {
    return this.posicaoVestibular;
  }

  public void setPosicaoVestibular(int posicaoVestibular) {
    this.posicaoVestibular = posicaoVestibular;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Aluno)) {
      return false;
    }
    Aluno aluno = (Aluno) o;
    return Objects.equals(ra, aluno.ra) && Objects.equals(nome, aluno.nome) && Objects.equals(email, aluno.email)
        && posicaoVestibular == aluno.posicaoVestibular;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ra, nome, email, posicaoVestibular);
  }

  @Override
  public String toString() {
    return "{" +
        " ra='" + getRa() + "'" +
        ", nome='" + getNome() + "'" +
        ", email='" + getEmail() + "'" +
        ", posicaoVestibular='" + getPosicaoVestibular() + "'" +
        "}";
  }

}
