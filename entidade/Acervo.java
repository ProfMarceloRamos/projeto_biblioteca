package entidade;

import java.util.List;

public abstract class Acervo {
  
  private int codigo;

  private String nome;

  private int qtdPaginas;

  private Editora editora;

  private List<Aluguel> listaAluguel;

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public Editora getEditora() {
    return editora;
  }

  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getQtdPaginas() {
    return qtdPaginas;
  }

  public void setQtdPaginas(int qtdPaginas) {
    this.qtdPaginas = qtdPaginas;
  }

  public List<Aluguel> getListaAluguel() {
    return listaAluguel;
  }

  public void setListaAluguel(List<Aluguel> listaAluguel) {
    this.listaAluguel = listaAluguel;
  }

  public abstract void imprimirInformacao();

  @Override
  public String toString() {
    return codigo+";"+nome+";"+qtdPaginas+";"+editora;
  }

}
