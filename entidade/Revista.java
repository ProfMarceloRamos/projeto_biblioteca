package entidade;

import enumeration.TipoRevistaEnum;

public class Revista extends Acervo {
 
  private TipoRevistaEnum tipoRevista;

  public TipoRevistaEnum getTipoRevista() {
    return tipoRevista;
  }

  public void setTipoRevista(TipoRevistaEnum tipoRevista) {
    this.tipoRevista = tipoRevista;
  }

  public void imprimirInformacao(){
    System.out.printf("A revista criada tem como nome %s e seu tipo é %s.\n",getNome(),getTipoRevista().getDescricao());
  }

  @Override
  public String toString() {
    return "REVISTA"+";"+getCodigo()+";"+getNome()+";"+getQtdPaginas()+";"+getEditora()+";"+tipoRevista.getCodigo();
  }
  
}
