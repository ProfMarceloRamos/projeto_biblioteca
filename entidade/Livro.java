package entidade;

public class Livro extends Acervo {

  private Autor autor;

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public void imprimirInformacao(){
    System.out.printf("O Livro criado tem como nome %s escrito pelo autor %s.\n",getNome(),getAutor().getNmAutor());
  }

  @Override
  public String toString() {
    return "LIVRO"+";"+getCodigo()+";"+getNome()+";"+getQtdPaginas()+";"+getEditora()+";"+autor;
  }

}