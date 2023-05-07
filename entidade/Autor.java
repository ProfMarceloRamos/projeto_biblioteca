package entidade;

public class Autor {

  private int codigo;
  
  private String nmAutor;

  private String idadeAutor;

  public Autor(){}

  public Autor(String nmAutor){
    this.nmAutor = nmAutor;
  }

  public Autor(int codigo, String nmAutor){
    this.codigo = codigo;
    this.nmAutor = nmAutor;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNmAutor() {
    return nmAutor;
  }

  public void setNmAutor(String nmAutor) {
    this.nmAutor = nmAutor;
  }

  public String getIdadeAutor() {
    return idadeAutor;
  }

  public void setIdadeAutor(String idadeAutor) {
    this.idadeAutor = idadeAutor;
  }

  @Override
  public String toString() {
    return this.codigo+";"+this.nmAutor;
  }

}
