package service;

import java.util.List;

import entidade.Usuario;
import repository.UsuarioRepositorio;

public class UsuarioService {
  
  private UsuarioRepositorio repositorio;

  private UsuarioRepositorio getRepositorio() {
    if(repositorio == null){
      repositorio = new UsuarioRepositorio();
    }
    return repositorio;
  }

  public void criarUsuario(Usuario usr){

    if(usr.getNmUsuario() == null || usr.getNmUsuario() == ""){
      System.out.println("Você precisa informar o nome do usuário!");
      return;
    }

    getRepositorio().criarUsuario(usr);
  }

  public int recuperarUltimaCodigoUsuario(){
    return getRepositorio().recuperarUltimaCodigoUsuario();
  }

  public List<Usuario> retornaListaUsuario(){
    return getRepositorio().retornaListaUsuario();
  }

}
