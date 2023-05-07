package entidade;

public class Editora {

  private int codigo;
  
  private String nmEditora;

  public Editora(){
  }

  public Editora(int codigo, String nmEditora){
    this.codigo = codigo;
    this.nmEditora = nmEditora;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNmEditora() {
    return nmEditora;
  }

  public void setNmEditora(String nmEditora) {
    this.nmEditora = nmEditora;
  }

  @Override
  public String toString() {
    return this.getCodigo()+";"+this.getNmEditora();
  }

}
