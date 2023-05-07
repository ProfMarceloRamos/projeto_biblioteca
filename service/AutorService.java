package service;

import java.util.List;

import entidade.Autor;
import repository.AutorRepositorio;

public class AutorService {
  
  private AutorRepositorio repositorio;

  private AutorRepositorio getRepositorio() {
    if(repositorio == null){
      repositorio = new AutorRepositorio();
    }
    return repositorio;
  }

  public void criarAutor(Autor usr){

    if(usr.getNmAutor() == null || usr.getNmAutor() == ""){
      System.out.println("Você precisa informar o nome do usuário!");
      return;
    }

    getRepositorio().criarAutor(usr);
  }

  public int recuperarUltimaCodigoAutor(){
    return getRepositorio().recuperarUltimaCodigoAutor();
  }

  public List<Autor> retornaListaAutor(){
    return getRepositorio().retornaListaAutor();
  }

  public Autor recuperarAutor(int codigo){
    Autor autor = getRepositorio().recuperarAutor(codigo);

    if(autor == null){
      System.out.println("O autor é desconhecido!");
    }
    return autor;
  }

}
