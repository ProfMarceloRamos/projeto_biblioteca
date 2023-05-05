package entidade;

import java.util.List;

public class Usuario {
  
  private int codigo;

  private String nmUsuario;

  private List<Aluguel> aluguel;

  public Usuario(int codigo, String nmUsuario){
    this.nmUsuario = nmUsuario;
    this.codigo = codigo;
  }

  public List<Aluguel> getAluguel() {
    return aluguel;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getNmUsuario() {
    return nmUsuario;
  }

  public void setAluguel(List<Aluguel> aluguel) {
    this.aluguel = aluguel;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public void setNmUsuario(String nmUsuario) {
    this.nmUsuario = nmUsuario;
  }

  @Override
  public String toString() {
    return codigo+";"+nmUsuario;
  }

}
