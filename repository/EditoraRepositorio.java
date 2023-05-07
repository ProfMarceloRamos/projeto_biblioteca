package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Editora;

public class EditoraRepositorio {
  
  private final static String arquivo_editora = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\git\\projeto_biblioteca\\arquivos\\editora.bib";

  private List<Editora> listaEditora;

  private List<Editora> getListaEditora() {
    if(listaEditora == null){
      listaEditora = new ArrayList<Editora>();
    }
    return listaEditora;
  }

  private void limparListaEditora(){
    listaEditora = new ArrayList<Editora>();
  }

  public void criarEditora(Editora Editora){
    File arq = new File(arquivo_editora);

    try {
      FileWriter fw = new FileWriter(arq, true);
      fw.write(Editora.toString());
      fw.write(System.lineSeparator());
      fw.close();
    } catch (Exception e) {
      System.out.println("Não foi possível criar o arquivo de usuário!");
    }
  }

  private void recuperarEditoras(){
    File arq = new File(arquivo_editora);

    limparListaEditora();

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        int codigo = new Integer(strVt[0]);
        String nmEditora = strVt[1];

        Editora Editora = new Editora(codigo, nmEditora);

        getListaEditora().add(Editora);

      }

      sc.close();

    } catch (IOException e) {
      System.out.println("Não foi possível ler o arquivo de Editora!");
    } catch (NumberFormatException e) {
      System.out.println("Não foi possível transformar o valor para um número!");
    }
  }

  public int recuperarUltimaCodigoEditora(){

    recuperarEditoras();

    if(getListaEditora().size() == 0){
      return 0;
    }

    return getListaEditora().get(getListaEditora().size() - 1).getCodigo();

  }

  public List<Editora> retornaListaEditora(){
    recuperarEditoras();
    return getListaEditora();
  }

  public Editora recuperarEditora(int codigo){
    List<Editora> listaEditora = retornaListaEditora();
    for (Editora Editora : listaEditora) {
      if(Editora.getCodigo() == codigo){
        return Editora;
      }
    }
    return null;
  }

}
