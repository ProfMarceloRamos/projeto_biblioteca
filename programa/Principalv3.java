package programa;

import java.util.Scanner;

import entidade.Acervo;
import entidade.Revista;
import entidade.Usuario;
import enumeration.TipoRevistaEnum;

public class Principalv3 {

  private int codigoUsuario;

  private int codigoAcervo;

  public int getCodigoUsuario() {
    return ++codigoUsuario;
  }

  public int getCodigoAcervo() {
    return ++codigoAcervo;
  }
  
  public static void main(String[] args) {
    
    Scanner s = new Scanner(System.in);
    Principalv3 principal = new Principalv3();

    int opcao = menu(s);

    executarAcao(opcao, s, principal);

    s.close();

  }

  public static int menu(Scanner s){

    System.out.println("**********MENU***********");
    System.out.println("1 - Criar usuário");
    System.out.println("2 - Criar acervo");
    System.out.println("*************************");
    System.out.print("Digite a opção do menu: ");

    int menu = s.nextInt();

    return menu;

  }

  public static void executarAcao(int opcao, Scanner s, Principalv3 principal){

    Usuario usr = null;
    Acervo acr = null;

    switch(opcao){
      case 1:
        usr = informacoesUsuario(s, principal);
        break;
      case 2:
        int op = menuAcervo(s);
        acr = informacoesAcervo(op, principal, s);
        break;
      default:
        System.out.println("Opção inválida!");
    }

    if(usr != null){
      System.out.printf("%d - %s \n",usr.getCodigo(),usr.getNmUsuario());
    }

    if(acr != null){
      System.out.printf("%d - %s \n", acr.getCodigo(), acr.getNome());
    }

  }

  public static Usuario informacoesUsuario(Scanner s, Principalv3 principal){

    System.out.print("Digite o nome do usuário: ");
    String nmUsr = s.next();

    Usuario retornoUsr = criarNovoUsuario(nmUsr, principal);

    return retornoUsr;

  }

  public static Usuario criarNovoUsuario(String nmUsuario, Principalv3 principal){
    
    Usuario usr = new Usuario(principal.getCodigoUsuario(), nmUsuario);

    return usr;

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

  public static Acervo informacoesAcervo(int opcao, Principalv3 principal, Scanner s){

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

  public static Acervo informacoesRevista(Scanner s, Principalv3 principal){

    // Essa linha é informada por conta de um problema da classe Scanner.
    s.nextLine();

    System.out.print("Digite o nome da revista: ");
    String nmRevista = s.nextLine();

    System.out.println("*******Tipo de Revistas*********");
    System.out.println("1 - Revista de Fofoca");
    System.out.println("2 - Revista de Política");
    System.out.println("3 - Revista de Religião");
    System.out.println("4 - Revista de Informática");
    System.out.print("Digite o tipo de revista: ");
    int tpRevista = s.nextInt();

    TipoRevistaEnum tr = null;
    switch(tpRevista){
      case 1:
        tr = TipoRevistaEnum.FOFOCA;
        break;
      case 2:
        tr = TipoRevistaEnum.POLITICA;
        break;
      case 3:
        tr = TipoRevistaEnum.RELIGIAO;
        break;
      case 4:
        tr = TipoRevistaEnum.INFORMATICA;
        break;
      default:
        System.out.println("Não existe o tipo de revista informado!");
    }

    Acervo revista = null;
    if(tr != null){
      revista = criarNovoAcervoRevista(principal, nmRevista, tr);
    }

    return revista;

  }

  public static Acervo criarNovoAcervoRevista(Principalv3 principal, String nomeAcervo, TipoRevistaEnum tipoRevista){

    Acervo acervo = new Revista();
    acervo.setCodigo(principal.getCodigoAcervo());
    acervo.setNome(nomeAcervo);

    Revista revista = (Revista)acervo;
    revista.setTipoRevista(tipoRevista);

    return acervo;
  }

}
