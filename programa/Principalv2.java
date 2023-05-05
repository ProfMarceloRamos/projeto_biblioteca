package programa;

import java.util.Scanner;

import entidade.Acervo;
import entidade.Revista;
import entidade.Usuario;
import enumeration.TipoRevistaEnum;

public class Principalv2 {

  private int codigoUsuario;

  private int codigoAcervo;

  public int getCodigoUsuario() {
    return ++codigoUsuario;
  }

  public int getCodigoAcervo() {
    return ++codigoAcervo;
  }
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    Principalv2 principal = new Principalv2();

    // System.out.print("Digite o nome do usuário: ");
    // String nmUsuario1 = sc.nextLine();

    // Usuario usr1 = criarNovoUsuario(nmUsuario1, principal);

    // System.out.printf("%d - %s \n", usr1.getCodigo(), usr1.getNmUsuario());

    // System.out.print("Digite o nome do usuário: ");
    // String nmUsuario2 = sc.nextLine();

    // Usuario usr2 = criarNovoUsuario(nmUsuario2, principal);

    // System.out.printf("%d - %s \n", usr2.getCodigo(), usr2.getNmUsuario());

    Revista a = (Revista)criarNovoAcervoRevista(principal, "Revista Caras", TipoRevistaEnum.FOFOCA);

    System.out.printf("%d - %s (%s) \n", a.getCodigo(), a.getNome(), a.getTipoRevista().getDescricao());

    sc.close();

  }

  public static Usuario criarNovoUsuario(String nmUsuario, Principalv2 principal){
    
    Usuario usr = new Usuario(principal.getCodigoUsuario(), nmUsuario);

    return usr;

  }

  public static Acervo criarNovoAcervoRevista(Principalv2 principal, String nomeAcervo, TipoRevistaEnum tipoRevista){

    Acervo acervo = new Revista();
    acervo.setCodigo(principal.getCodigoAcervo());
    acervo.setNome(nomeAcervo);

    Revista revista = (Revista)acervo;
    revista.setTipoRevista(tipoRevista);

    return acervo;
  }

}
