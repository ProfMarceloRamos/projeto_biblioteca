package service;

import java.util.List;

import entidade.Editora;
import repository.EditoraRepositorio;

public class EditoraService {
  
  private EditoraRepositorio repositorio;

  private EditoraRepositorio getRepositorio() {
    if(repositorio == null){
      repositorio = new EditoraRepositorio();
    }
    return repositorio;
  }

  public void criarEditora(Editora usr){

    if(usr.getNmEditora() == null || usr.getNmEditora() == ""){
      System.out.println("Você precisa informar o nome da editora!");
      return;
    }

    getRepositorio().criarEditora(usr);
  }

  public int recuperarUltimaCodigoEditora(){
    return getRepositorio().recuperarUltimaCodigoEditora();
  }

  public List<Editora> retornaListaEditora(){
    return getRepositorio().retornaListaEditora();
  }

  public Editora recuperarEditora(int codigo){
    Editora editora = getRepositorio().recuperarEditora(codigo);

    if(editora == null){
      System.out.println("A Editora é desconhecida!");
    }
    return editora;
  }

}
