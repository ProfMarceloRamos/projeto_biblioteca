package programa;

import java.util.List;
import java.util.Scanner;

import entidade.Acervo;
import entidade.Editora;
import entidade.Revista;
import entidade.Usuario;
import enumeration.TipoRevistaEnum;
import service.AcervoService;
import service.UsuarioService;

public class Principalv6 {

  private UsuarioService usrService;

  private AcervoService acrService;

  public UsuarioService getUsrService() {
    if(usrService == null){
      usrService = new UsuarioService();
    }
    return usrService;
  }

  public AcervoService getAcervoService() {
    if(acrService == null){
      acrService = new AcervoService();
    }
    return acrService;
  }
  
  public static void main(String[] args) {
    
    Scanner s = new Scanner(System.in);
    Principalv6 principal = new Principalv6();

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

  public static void executarAcao(int opcao, Scanner s, Principalv6 principal){

    switch(opcao){
      case 1:
        principal.criarUsuario(s);
        break;
      case 2:
        int op = menuAcervo(s);
        principal.informacoesAcervo(op, principal, s);
        break;
      case 3:
        principal.listarUsuarios();
        break;
      case 4:
        principal.listarAcervos();
        break;
      case 99:
        System.out.println("Que pena! Volte logo!");
        break;
      default:
        System.out.println("Opção inválida!");
    }

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

  public void criarUsuario(Scanner s){
    s.nextLine();

    System.out.print("Digite o nome do usuário: ");
    String nmUsr = s.nextLine();

    int codigo = getUsrService().recuperarUltimaCodigoUsuario();

    Usuario usr = new Usuario(codigo + 1, nmUsr);

    getUsrService().criarUsuario(usr);

  }

  public void listarUsuarios(){

    List<Usuario> listaUsr = getUsrService().retornaListaUsuario();

    System.out.println("****LISTA DE USUÁRIO****");
    for (Usuario usuario : listaUsr) {
      System.out.printf("%d - %s \n",usuario.getCodigo(), usuario.getNmUsuario());
    }
    System.out.println("************************");

  }

  public Acervo informacoesAcervo(int opcao, Principalv6 principal, Scanner s){

    Acervo acervo = null;
    switch(opcao){
      case 1:
        informacoesRevista(s, principal);
        break;
      case 2:
        break;
      default:
        System.out.println("Opção do acervo inválida!");
    }

    return acervo;

  }

  public void informacoesRevista(Scanner s, Principalv6 principal){

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

    Revista revista = new Revista();

    int codigo = getAcervoService().recuperarUltimoCodigoAcervo();

    revista.setCodigo(codigo + 1);
    revista.setNome(nmRevista);
    revista.setTipoRevista(tr);

    Editora editora = new Editora();
    editora.setNmEditora("Editora IJD");
    revista.setEditora(editora);

    getAcervoService().criarAcervo(revista);

  }

  public void listarAcervos(){

    List<Acervo> listaAcervo = getAcervoService().retornaListaAcervo();

    System.out.println("****LISTA DE ACERVOS****");
    for (Acervo acervo : listaAcervo) {
      System.out.println(acervo.getCodigo());
    }
    System.out.println("************************");

  }

}
