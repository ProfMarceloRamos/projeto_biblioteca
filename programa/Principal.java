package programa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidade.Acervo;
import entidade.Autor;
import entidade.Biblioteca;
import entidade.Aluguel;
import entidade.Editora;
import entidade.Livro;
import entidade.Revista;
import entidade.Usuario;
import enumeration.TipoRevistaEnum;

public class Principal {

  private static int codigoUsuario;

  public static void main(String[] args) {
    
    Acervo l = new Livro();
    l.setNome("Livro 1");
    l.setQtdPaginas(5);
    
    Editora e = new Editora();
    e.setNmEditora("Editora 1");
    l.setEditora(e);

    Autor a = new Autor();
    a.setNmAutor("Autor do livro");

    Livro livro = (Livro)l;

    livro.setAutor(a);
    
    impressao(livro);

    //System.out.printf("O título do livro é %s do autor %s é da editora %s\n",l.getNome(), l.getAutor().getNmAutor(), l.getEditora().getNmEditora());

    Acervo r = new Revista();
    r.setNome("Revista de Java");
    r.setQtdPaginas(15);

    Revista revista = (Revista)r;

    revista.setTipoRevista(TipoRevistaEnum.INFORMATICA);

    //System.out.printf("A revista do titulo %s tem %d paginas e o seu tipo é %s\n",r.getNome(),r.getQtdPaginas(),r.getTipoRevista());

    impressao(revista);

    // Aluguel aluguel = new Aluguel();
    // aluguel.setAcervo(r);

    LocalDate dtEmprestimo = LocalDate.now();

    //aluguel.setDtEmprestimo(dtEmprestimo);

    LocalDate dtPrevistaDevolucao = dtEmprestimo.plusDays(10);
    //aluguel.setDtPrevistaDevolucao(dtPrevistaDevolucao);

    Usuario usrMarcelo = criarNovoUsuario("Marcelo");
    Usuario usrMaria = criarNovoUsuario("Maria");
    Usuario usrJose = criarNovoUsuario("José");

    List<Usuario> listaUsuario = new ArrayList<Usuario>();
    listaUsuario.add(usrMarcelo);
    listaUsuario.add(usrMaria);
    listaUsuario.add(usrJose);

    Biblioteca biblioteca = new Biblioteca();
    biblioteca.setListaUsuario(listaUsuario);

  }

  public static void impressao(Acervo a){
    a.imprimirInformacao();
  }

  public static Usuario criarNovoUsuario(String nmUsuario){

    codigoUsuario = codigoUsuario + 1;

    Usuario usr = new Usuario(codigoUsuario, nmUsuario);
    return usr;

  }

}
