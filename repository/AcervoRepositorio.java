package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Acervo;
import entidade.Autor;
import entidade.Editora;
import entidade.Livro;
import entidade.Revista;
import enumeration.TipoRevistaEnum;
import service.AutorService;
import service.EditoraService;

public class AcervoRepositorio {
  
  private final static String arquivo_acervo = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\git\\projeto_biblioteca\\arquivos\\acervo.bib";

  private AutorService autorService;
  
  private EditoraService editoraService;

  private List<Acervo> listaAcervo;

  private List<Acervo> getListaAcervo(){
    if(listaAcervo == null){
      listaAcervo = new ArrayList<Acervo>();
    }
    return listaAcervo;
  }

  private void limparListaAcervo(){
    listaAcervo = new ArrayList<Acervo>();
  }

  public AutorService getAutorService() {
    if(autorService == null){
      autorService = new AutorService();
    }
    return autorService;
  }

  public EditoraService getEditoraService() {
    if(editoraService == null){
      editoraService = new EditoraService();
    }
    return editoraService;
  }

  public void criarAcervo(Acervo acr){
    File arq = new File(arquivo_acervo);

    try {
      FileWriter fw = new FileWriter(arq, true);
      fw.write(acr.toString());
      fw.write(System.lineSeparator());
      fw.close();
    } catch (Exception e) {
      System.out.println("Não foi possível criar o arquivo de usuário!");
    }
  }

  public int recuperarUltimoCodigoAcervo(){
    recuperarAcervos();
    if(getListaAcervo().size() == 0){
      return 0;
    }

    return getListaAcervo().get(getListaAcervo().size() - 1).getCodigo();
  }

  public List<Acervo> retornaListaAcervo(){
    recuperarAcervos();
    return getListaAcervo();
  }

  private void recuperarAcervos(){
    File arq = new File(arquivo_acervo);

    limparListaAcervo();

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        String tipoAcervo = strVt[0];

        Editora editora = null;
        switch(tipoAcervo){
          case "LIVRO":
            Livro livro = new Livro();
            livro.setCodigo(new Integer(strVt[1]));
            
            Autor autor = getAutorService().recuperarAutor(new Integer(strVt[5]));
            if(autor == null){
              return;
            }
            livro.setAutor(autor);

            editora = getEditoraService().recuperarEditora(new Integer(strVt[4]));
            livro.setEditora(editora);
            livro.setNome(strVt[2]);
            livro.setQtdPaginas(new Integer(strVt[3]));
            getListaAcervo().add(livro);
            break;
          case "REVISTA":
            Revista revista = new Revista();
            revista.setCodigo(new Integer(strVt[1]));
            
            editora = getEditoraService().recuperarEditora(new Integer(strVt[4]));
            
            revista.setEditora(editora);
            revista.setNome(strVt[2]);
            revista.setQtdPaginas(new Integer(strVt[3]));
            TipoRevistaEnum tipoRevista = TipoRevistaEnum.valueOf(new Integer(strVt[5]));
            revista.setTipoRevista(tipoRevista);
            getListaAcervo().add(revista);
            break;
        }

      }

      sc.close();

    } catch (IOException e) {
      System.out.println("Não foi possível ler o arquivo de acervo!");
    } catch (NumberFormatException e) {
      System.out.println("Não foi possível transformar o valor para um número! - Acervo");
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public Acervo recuperarAcervo(int codigo){
    List<Acervo> listaAcervo = retornaListaAcervo();
    for (Acervo acervo : listaAcervo) {
      if(acervo.getCodigo() == codigo){
        return acervo;
      }
    }
    return null;
  }

}
