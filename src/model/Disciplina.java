package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "disciplinas")
public class Disciplina {
  @Id
  @Column(name = "codigo_disc")
  @NotNull
  private int codigoDisc;

  @Column(name = "nome_disc")
  @NotNull
  private String nomeDisc;

  @Column(name = "carga_horaria")
  @NotNull
  private int cargaHoraria;

  public int getCodigoDisc() {
    return this.codigoDisc;
  }

  public void setCodigoDisc(int codigoDisc) {
    this.codigoDisc = codigoDisc;
  }

  public String getNomeDisc() {
    return this.nomeDisc;
  }

  public void setNomeDisc(String nomeDisc) {
    this.nomeDisc = nomeDisc;
  }

  public int getCargaHoraria() {
    return this.cargaHoraria;
  }

  public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Disciplina)) {
      return false;
    }
    Disciplina disciplina = (Disciplina) o;
    return codigoDisc == disciplina.codigoDisc && Objects.equals(nomeDisc, disciplina.nomeDisc)
        && cargaHoraria == disciplina.cargaHoraria;
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigoDisc, nomeDisc, cargaHoraria);
  }

  @Override
  public String toString() {
    return "{" +
        " codigoDisc='" + getCodigoDisc() + "'" +
        ", nomeDisc='" + getNomeDisc() + "'" +
        ", cargaHoraria='" + getCargaHoraria() + "'" +
        "}";
  }
}