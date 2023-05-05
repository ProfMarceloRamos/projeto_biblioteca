package entidade;

public class Autor {
  
  private String nmAutor;

  private String idadeAutor;

  public Autor(){}

  public Autor(String nmAutor){
    this.nmAutor = nmAutor;
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

}
