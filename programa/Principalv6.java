package programa;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import entidade.Acervo;
import entidade.Aluguel;
import entidade.Autor;
import entidade.Editora;
import entidade.Livro;
import entidade.Revista;
import entidade.Usuario;
import enumeration.TipoMovimentacaoAcervoEnum;
import enumeration.TipoRevistaEnum;
import service.AcervoService;
import service.AluguelService;
import service.AutorService;
import service.EditoraService;
import service.UsuarioService;

public class Principalv6 {

  private UsuarioService usrService;

  private AcervoService acrService;
  
  private AutorService autorService;

  private EditoraService editoraService;

  private AluguelService aluguelService;

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

  public AluguelService getAluguelService() {
    if(aluguelService == null){
      aluguelService = new AluguelService();
    }
    return aluguelService;
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
    System.out.println("2 - Listar usuários");
    System.out.println("3 - Criar acervo");
    System.out.println("4 - Listar acervos");
    System.out.println("5 - Criar editora");
    System.out.println("6 - Listar editoras");
    System.out.println("7 - Criar autor");
    System.out.println("8 - Listar autores");
    System.out.println("9 - Alugar");
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
        principal.listarUsuarios();
        break;
      case 3:
        int op = menuAcervo(s);
        principal.informacoesAcervo(op, principal, s);
        break;
      case 4:
        principal.listarAcervos();
        break;
      case 5:
        principal.criarEditora(s);
        break;
      case 6:
        principal.listarEditoras();
        break;
      case 7:
        principal.criarAutor(s);
        break;
      case 8:
        principal.listarAutores();
        break;
      case 9:
        principal.alugar(s);
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

  public Acervo informacoesAcervo(int opcao, Principalv6 principal, Scanner s){

    Acervo acervo = null;
    switch(opcao){
      case 1:
        informacoesRevista(s, principal);
        break;
      case 2:
        informacoesLivro(s, principal);
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

    System.out.print("Digite a quantidade de páginas do livro: ");
    int qtdPaginas = s.nextInt();

    TipoRevistaEnum tr = TipoRevistaEnum.valueOf(tpRevista);
    if(tr == null){
      System.out.println("Não existe o tipo de revista informado!");
    }

    Revista revista = new Revista();

    int codigo = getAcervoService().recuperarUltimoCodigoAcervo();

    revista.setCodigo(codigo + 1);
    revista.setNome(nmRevista);
    revista.setQtdPaginas(qtdPaginas);
    revista.setTipoRevista(tr);

    System.out.println("*******Editoras*********");
    List<Editora> listaEditora = getEditoraService().retornaListaEditora();
    for (Editora editora : listaEditora) {
      System.out.printf("%d - %s\n",editora.getCodigo(), editora.getNmEditora());
    }
    System.out.print("Digite o código da editora: ");
    int cdEditora = s.nextInt();

    Editora editora = getEditoraService().recuperarEditora(cdEditora);
    if(editora == null){
      return;
    }

    revista.setEditora(editora);

    getAcervoService().criarAcervo(revista);

  }

  public void informacoesLivro(Scanner s, Principalv6 principal){

    // Essa linha é informada por conta de um problema da classe Scanner.
    s.nextLine();

    System.out.print("Digite o nome do livro: ");
    String nmlivro = s.nextLine();

    System.out.print("Digite a quantidade de páginas do livro: ");
    int qtdPaginas = s.nextInt();

    System.out.println("*******Autores*********");
    List<Autor> listaAutor = getAutorService().retornaListaAutor();
    for (Autor autor : listaAutor) {
      System.out.printf("%d - %s\n",autor.getCodigo(), autor.getNmAutor());
    }
    System.out.print("Digite o código do autor: ");
    int cdAutor = s.nextInt();

    Autor autor = getAutorService().recuperarAutor(cdAutor);
    if(autor == null){
      return;
    }

    Livro livro = new Livro();

    int codigo = getAcervoService().recuperarUltimoCodigoAcervo();

    livro.setCodigo(codigo + 1);
    livro.setNome(nmlivro);
    livro.setQtdPaginas(qtdPaginas);

    livro.setAutor(autor);

    System.out.println("*******Editoras*********");
    List<Editora> listaEditora = getEditoraService().retornaListaEditora();
    for (Editora editora : listaEditora) {
      System.out.printf("%d - %s\n",editora.getCodigo(), editora.getNmEditora());
    }
    System.out.print("Digite o código da editora: ");
    int cdEditora = s.nextInt();

    Editora editora = getEditoraService().recuperarEditora(cdEditora);
    if(editora == null){
      return;
    }
    
    livro.setEditora(editora);

    getAcervoService().criarAcervo(livro);

  }
  
  public void criarAutor(Scanner s){
    s.nextLine();
    
    System.out.print("Digite o nome do autor: ");
    String nmAutor = s.nextLine();
    
    int codigo = getAutorService().recuperarUltimaCodigoAutor();
    
    Autor autor = new Autor(codigo + 1, nmAutor);
    
    getAutorService().criarAutor(autor);
    
  }
  
  public void criarEditora(Scanner s){
    s.nextLine();

    System.out.print("Digite o nome da editora: ");
    String nmEditora = s.nextLine();
    
    int codigo = getEditoraService().recuperarUltimaCodigoEditora();
    
    Editora editora = new Editora(codigo + 1, nmEditora);
    
    getEditoraService().criarEditora(editora);
    
  }

  public void listarEditoras(){
  
    List<Editora> listaEditora = getEditoraService().retornaListaEditora();
  
    System.out.println("****LISTA DE EDITORAS****");
    for (Editora editora : listaEditora) {
      System.out.printf("%d - %s\n",editora.getCodigo(),editora.getNmEditora());
    }
    System.out.println("************************");
  
  }
  
  public void listarAutores(){
  
    List<Autor> listaAutores = getAutorService().retornaListaAutor();
  
    System.out.println("****LISTA DE AUTORES****");
    for (Autor autor : listaAutores) {
      System.out.printf("%d - %s\n",autor.getCodigo(),autor.getNmAutor());
    }
    System.out.println("************************");
  
  }

  public void listarAcervos(){

    List<Acervo> listaAcervo = getAcervoService().retornaListaAcervo();

    System.out.println("****LISTA DE ACERVOS****");
    for (Acervo acervo : listaAcervo) {
      if(acervo instanceof Revista){
        Revista r = (Revista)acervo;
        r.imprimirInformacao();
      } else if(acervo instanceof Livro){
        Livro l = (Livro)acervo;
        l.imprimirInformacao();
      }
    }
    System.out.println("************************");

  }

  public void listarUsuarios(){

    List<Usuario> listaUsr = getUsrService().retornaListaUsuario();

    System.out.println("****LISTA DE USUÁRIO****");
    for (Usuario usuario : listaUsr) {
      System.out.printf("%d - %s \n",usuario.getCodigo(), usuario.getNmUsuario());
    }
    System.out.println("************************");

  }

  public void alugar(Scanner s){

    listarUsuarios();
    System.out.print("Digite o código do usuário: ");
    int cdUsr = s.nextInt();

    Usuario usuario = getUsrService().recuperarUsuario(cdUsr);

    listarAcervos();
    System.out.print("Digite o código do acervo: ");
    int cdAcr = s.nextInt();

    Acervo acervo = getAcervoService().recuperarAcervo(cdAcr);

    int codigo = getAluguelService().recuperarUltimoCodigoAluguel();

    Aluguel aluguel = new Aluguel(codigo + 1, TipoMovimentacaoAcervoEnum.ALUGADO, usuario, acervo, LocalDate.now());

    getAluguelService().alugarAcervo(aluguel);
  }

}
