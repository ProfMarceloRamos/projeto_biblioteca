package entidade;

import java.time.LocalDate;

public class Aluguel {
  
  private Acervo acervo;

  private LocalDate dtEmprestimo;

  private LocalDate dtDevolucao;

  private LocalDate dtPrevistaDevolucao;

  public Acervo getAcervo() {
    return acervo;
  }

  public LocalDate getDtDevolucao() {
    return dtDevolucao;
  }

  public LocalDate getDtEmprestimo() {
    return dtEmprestimo;
  }

  public LocalDate getDtPrevistaDevolucao() {
    return dtPrevistaDevolucao;
  }

  public void setAcervo(Acervo acervo) {
    this.acervo = acervo;
  }

  public void setDtDevolucao(LocalDate dtDevolucao) {
    this.dtDevolucao = dtDevolucao;
  }

  public void setDtEmprestimo(LocalDate dtEmprestimo) {
    this.dtEmprestimo = dtEmprestimo;
  }

  public void setDtPrevistaDevolucao(LocalDate dtPrevistaDevolucao) {
    this.dtPrevistaDevolucao = dtPrevistaDevolucao;
  }

}
