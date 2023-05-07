package entidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enumeration.TipoMovimentacaoAcervoEnum;

public class Aluguel {

  private static final int qtdDiasEmprestimo = 5;

  private int codigo;

  private TipoMovimentacaoAcervoEnum tipoAluguel;
  
  private Acervo acervo;

  private Usuario usuario;

  private LocalDate dtEmprestimo;

  private LocalDate dtDevolucao;

  private LocalDate dtPrevistaDevolucao;

  public Aluguel(int codigo, TipoMovimentacaoAcervoEnum tipoAluguel, Usuario usuario, Acervo acervo, LocalDate dtEmprestimo){
    this.codigo = codigo;
    this.tipoAluguel = tipoAluguel;
    this.acervo = acervo;
    this.usuario = usuario;
    this.dtEmprestimo = dtEmprestimo;
    setAtualizaDtPrevistaDevolucao();
  }

  public Aluguel(int codigo, TipoMovimentacaoAcervoEnum tipoAluguel, Usuario usuario, Acervo acervo, LocalDate dtEmprestimo, LocalDate dtPrevistaDevolucao){
    this.codigo = codigo;
    this.tipoAluguel = tipoAluguel;
    this.acervo = acervo;
    this.usuario = usuario;
    this.dtEmprestimo = dtEmprestimo;
    this.dtPrevistaDevolucao = dtPrevistaDevolucao;
  }

  public Aluguel(int codigo, TipoMovimentacaoAcervoEnum tipoAluguel, Usuario usuario, Acervo acervo, LocalDate dtEmprestimo, LocalDate dtPrevistaDevolucao, LocalDate dtDevolucao){
    this.codigo = codigo;
    this.tipoAluguel = tipoAluguel;
    this.acervo = acervo;
    this.usuario = usuario;
    this.dtEmprestimo = dtEmprestimo;
    this.dtPrevistaDevolucao = dtPrevistaDevolucao;
    this.dtDevolucao = dtDevolucao;
  }

  public int getCodigo() {
    return codigo;
  }

  public Acervo getAcervo() {
    return acervo;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public LocalDate getDtDevolucao() {
    return dtDevolucao;
  }

  public String getDtDevolucaoFormatado() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return dtf.format(this.dtDevolucao);
  }

  public LocalDate getDtEmprestimo() {
    return dtEmprestimo;
  }

  public String getDtEmprestimoFormatado() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return dtf.format(this.dtEmprestimo);
  }

  public LocalDate getDtPrevistaDevolucao() {
    return dtPrevistaDevolucao;
  }

  public String getDtPrevistaDevolucaoFormatado() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return dtf.format(this.dtPrevistaDevolucao);
  }

  public TipoMovimentacaoAcervoEnum getTipoAluguel() {
    return tipoAluguel;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public void setAcervo(Acervo acervo) {
    this.acervo = acervo;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
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

  public void setAtualizaDtPrevistaDevolucao() {
    this.dtPrevistaDevolucao = dtEmprestimo.plusDays(qtdDiasEmprestimo);
  }

  public void setTipoAluguel(TipoMovimentacaoAcervoEnum tipoAluguel) {
    this.tipoAluguel = tipoAluguel;
  }

  @Override
  public String toString() {
    if(this.tipoAluguel.getCodigo() == TipoMovimentacaoAcervoEnum.ALUGADO.getCodigo()){
      return this.codigo+";"+this.tipoAluguel.getCodigo()+";"+this.usuario.getCodigo()+";"+this.acervo.getCodigo()+";"+getDtEmprestimoFormatado()+";"+getDtPrevistaDevolucaoFormatado();
    }
    return this.codigo+";"+this.tipoAluguel.getCodigo()+";"+this.usuario.getCodigo()+";"+this.acervo.getCodigo()+";"+getDtEmprestimoFormatado()+";"+getDtPrevistaDevolucaoFormatado()+";"+getDtDevolucaoFormatado();
  }

}
