package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Autor;

public class AutorRepositorio {
  
  private final static String arquivo_autor = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\git\\projeto_biblioteca\\arquivos\\autor.bib";

  private List<Autor> listaAutor;

  private List<Autor> getListaAutor() {
    if(listaAutor == null){
      listaAutor = new ArrayList<Autor>();
    }
    return listaAutor;
  }

  private void limparListaAutor(){
    listaAutor = new ArrayList<Autor>();
  }

  public void criarAutor(Autor autor){
    File arq = new File(arquivo_autor);

    try {
      FileWriter fw = new FileWriter(arq, true);
      fw.write(autor.toString());
      fw.write(System.lineSeparator());
      fw.close();
    } catch (Exception e) {
      System.out.println("Não foi possível criar o arquivo de usuário!");
    }
  }

  private void recuperarAutores(){
    File arq = new File(arquivo_autor);

    limparListaAutor();

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        int codigo = new Integer(strVt[0]);
        String nmAutor = strVt[1];

        Autor autor = new Autor(codigo, nmAutor);

        getListaAutor().add(autor);

      }

      sc.close();

    } catch (IOException e) {
      System.out.println("Não foi possível ler o arquivo de autor!");
    } catch (NumberFormatException e) {
      System.out.println("Não foi possível transformar o valor para um número!");
    }
  }

  public int recuperarUltimaCodigoAutor(){

    recuperarAutores();

    if(getListaAutor().size() == 0){
      return 0;
    }

    return getListaAutor().get(getListaAutor().size() - 1).getCodigo();

  }

  public List<Autor> retornaListaAutor(){
    recuperarAutores();
    return getListaAutor();
  }

  public Autor recuperarAutor(int codigo){
    List<Autor> listaAutor = retornaListaAutor();
    for (Autor autor : listaAutor) {
      if(autor.getCodigo() == codigo){
        return autor;
      }
    }
    return null;
  }

}
