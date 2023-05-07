package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Usuario;

public class UsuarioRepositorio {
  
  private final static String arquivo_usuario = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\git\\projeto_biblioteca\\arquivos\\usuario.bib";

  private List<Usuario> listaUsuario;

  private List<Usuario> getListaUsuario() {
    if(listaUsuario == null){
      listaUsuario = new ArrayList<Usuario>();
    }
    return listaUsuario;
  }

  private void limparListaUsuario(){
    listaUsuario = new ArrayList<Usuario>();
  }

  public void criarUsuario(Usuario usr){
    File arq = new File(arquivo_usuario);

    try {
      FileWriter fw = new FileWriter(arq, true);
      fw.write(usr.toString());
      fw.write(System.lineSeparator());
      fw.close();
    } catch (Exception e) {
      System.out.println("Não foi possível criar o arquivo de usuário!");
    }
  }

  private void recuperarUsuarios(){
    File arq = new File(arquivo_usuario);

    limparListaUsuario();

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        int codigo = new Integer(strVt[0]);
        String nmUsr = strVt[1];

        Usuario usr = new Usuario(codigo, nmUsr);

        getListaUsuario().add(usr);

      }

      sc.close();

    } catch (IOException e) {
      System.out.println("Não foi possível ler o arquivo de usuário!");
    } catch (NumberFormatException e) {
      System.out.println("Não foi possível transformar o valor para um número! - Usuário");
    }
  }

  public int recuperarUltimaCodigoUsuario(){
    recuperarUsuarios();

    if(getListaUsuario().size() == 0){
      return 0;
    }

    return getListaUsuario().get(getListaUsuario().size() - 1).getCodigo();

  }

  public List<Usuario> retornaListaUsuario(){
    recuperarUsuarios();
    return getListaUsuario();
  }

  public Usuario recuperarUsuario(int codigo){
    List<Usuario> listaUsuario = retornaListaUsuario();
    for (Usuario usuario : listaUsuario) {
      if(usuario.getCodigo() == codigo){
        return usuario;
      }
    }
    return null;
  }

}
