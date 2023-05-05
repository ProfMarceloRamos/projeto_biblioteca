package programa;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Acervo;
import entidade.Revista;
import entidade.Usuario;
import enumeration.TipoRevistaEnum;

public class Principalv5 {

  private int codigoUsuario;

  private int codigoAcervo;

  private List<Usuario> listaUsuario;

  private List<Acervo> listaAcervo;

  private final static String arquivo_usuario = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\projeto_biblioteca\\arquivos\\usuario.bib";
  private final static String arquivo_acervo = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\projeto_biblioteca\\arquivos\\acervo.bib";

  public int getCodigoUsuario() {
    return ++codigoUsuario;
  }

  public int getCodigoAcervo() {
    return ++codigoAcervo;
  }

  public List<Acervo> getListaAcervo() {
    return listaAcervo;
  }

  public List<Usuario> getListaUsuario() {
    return listaUsuario;
  }

  public void setListaAcervo(Acervo acr) {
    if(listaAcervo == null){
      listaAcervo = new ArrayList<Acervo>();
    }
    listaAcervo.add(acr);
  }

  public void setListaUsuario(Usuario usr) {
    if(listaUsuario == null){
      listaUsuario = new ArrayList<Usuario>();
    }
    listaUsuario.add(usr);
  }
  
  public static void main(String[] args) {
    
    Scanner s = new Scanner(System.in);
    Principalv5 principal = new Principalv5();

    int opcao = 0;
    do{

      opcao = menu(s);

      executarAcao(opcao, s, principal);

    }while(opcao != 99);
    

    s.close();

  }

  public static int menu(Scanner s){

    System.out.println("**********MENU***********");
    System.out.println("1 - Criar usuário");
    System.out.println("2 - Criar acervo");
    System.out.println("3 - Listar usuários");
    System.out.println("4 - Listar acervos");
    System.out.println("99 - FIM");
    System.out.println("*************************");
    System.out.print("Digite a opção do menu: ");

    int menu = s.nextInt();

    return menu;

  }

  public static void executarAcao(int opcao, Scanner s, Principalv5 principal){

    Usuario usr = null;
    Acervo acr = null;

    switch(opcao){
      case 1:
        usr = informacoesUsuario(s, principal);
        criarArquivoUsuario(usr);
        principal.setListaUsuario(usr);
        break;
      case 2:
        int op = menuAcervo(s);
        acr = informacoesAcervo(op, principal, s);
        principal.setListaAcervo(acr);
        break;
      case 3:
        //listarUsuarios(principal);
        lerArquivoUsuario();
        break;
      case 4:
        listarAcervos(principal);
        break;
      case 99:
        System.out.println("Que pena! Volte logo!");
        break;
      default:
        System.out.println("Opção inválida!");
    }

    // if(usr != null){
    //   System.out.printf("%d - %s \n",usr.getCodigo(),usr.getNmUsuario());
    // }

    // if(acr != null){
    //   acr.imprimirInformacao();
    // }

  }

  public static Usuario informacoesUsuario(Scanner s, Principalv5 principal){

    s.nextLine();

    System.out.print("Digite o nome do usuário: ");
    String nmUsr = s.nextLine();

    Usuario retornoUsr = criarNovoUsuario(nmUsr, principal);

    return retornoUsr;

  }

  public static void criarArquivoUsuario(Usuario usr){

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

  public static void lerArquivoUsuario(){
    File arq = new File(arquivo_usuario);

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        System.out.println(strVt[0] + " - " + strVt[1]);

      }

      //sc.close();

    } catch (Exception e) {
      System.out.println("Não foi possível ler o arquivo de usuário!");
    }

  }

  public static Usuario criarNovoUsuario(String nmUsuario, Principalv5 principal){
    
    int cod = recuperarUltimaCodigoUsuario();

    Usuario usr = new Usuario(cod + 1, nmUsuario);

    return usr;

  }

  public static int recuperarUltimaCodigoUsuario(){

    File arq = new File(arquivo_usuario);
    int ultCod = 0;

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        ultCod = new Integer(strVt[0]);

      }

      sc.close();

    } catch (Exception e) {
      System.out.println("Não foi possível ler o arquivo de usuário!");
    }

    return ultCod;
  }

  public static int menuAcervo(Scanner s){
    System.out.println("**********ACERVO***********");
    System.out.println("1 - Criar revista");
    System.out.println("2 - Criar livro");
    System.out.println("*************************");
    System.out.print("Digite a opção do acervo: ");

    int acervo = s.nextInt();

    return acervo;
  }

  public static Acervo informacoesAcervo(int opcao, Principalv5 principal, Scanner s){

    Acervo acervo = null;
    switch(opcao){
      case 1:
        acervo = informacoesRevista(s, principal);
        break;
      case 2:
        break;
      default:
        System.out.println("Opção do acervo inválida!");
    }

    return acervo;

  }

  public static Acervo informacoesRevista(Scanner s, Principalv5 principal){

    // Essa linha é informada por conta de um problema da classe Scanner.
    s.nextLine();

    System.out.print("Digite o nome da revista: ");
    String nmRevista = s.nextLine();

    System.out.println("*******Tipo de Revistas*********");
    TipoRevistaEnum.imprimeTiposRevistas();
    System.out.print("Digite o tipo de revista: ");
    int tpRevista = s.nextInt();

    TipoRevistaEnum tr = TipoRevistaEnum.valueOf(tpRevista);
    if(tr == null){
      System.out.println("Não existe o tipo de revista informado!");
    }

    Acervo revista = null;
    if(tr != null){
      revista = criarNovoAcervoRevista(principal, nmRevista, tr);
    }

    return revista;

  }

  public static Acervo criarNovoAcervoRevista(Principalv5 principal, String nomeAcervo, TipoRevistaEnum tipoRevista){

    Acervo acervo = new Revista();
    acervo.setCodigo(principal.getCodigoAcervo());
    acervo.setNome(nomeAcervo);

    Revista revista = (Revista)acervo;
    revista.setTipoRevista(tipoRevista);

    return acervo;
  }

  public static void listarUsuarios(Principalv5 principal){
    System.out.println("****LISTA DE USUÁRIO****");
    for(Usuario usr : principal.getListaUsuario()){
      System.out.println(usr.toString());
    }
    System.out.println("************************");
  }

  public static void listarAcervos(Principalv5 principal){
    System.out.println("****LISTA DE ACERVO****");
    for(Acervo acr : principal.listaAcervo){
      acr.imprimirInformacao();
    }
    System.out.println("***********************");
  }

}
