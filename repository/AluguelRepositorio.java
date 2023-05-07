package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidade.Acervo;
import entidade.Aluguel;
import entidade.Usuario;
import enumeration.TipoMovimentacaoAcervoEnum;
import service.AcervoService;
import service.UsuarioService;

public class AluguelRepositorio {

  private final static String arquivo_alugados = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\git\\projeto_biblioteca\\arquivos\\alugados.bib";
  private final static String arquivo_devolvidos = "C:\\Estudos\\Aulas\\Lógica com Java\\Março\\git\\projeto_biblioteca\\arquivos\\devolvidos.bib";

  private UsuarioService usuarioService;
  
  private AcervoService acervoService;

  private List<Aluguel> listaAluguel;

  private List<Aluguel> getListaAluguel(){
    if(listaAluguel == null){
      listaAluguel = new ArrayList<Aluguel>();
    }
    return listaAluguel;
  }

  private void limparListaAluguel(){
    listaAluguel = new ArrayList<Aluguel>();
  } 

  private UsuarioService getUsuarioService() {
    if(usuarioService == null){
      usuarioService = new UsuarioService();
    }
    return usuarioService;
  }
  
  private AcervoService getAcervoService() {
    if(acervoService == null){
      acervoService = new AcervoService();
    }
    return acervoService;
  }
  
  public void alugarAcervo(Aluguel aluguel){
    File arq = new File(arquivo_alugados);

    try {
      FileWriter fw = new FileWriter(arq, true);
      fw.write(aluguel.toString());
      fw.write(System.lineSeparator());
      fw.close();
    } catch (Exception e) {
      System.out.println("Não foi possível criar o arquivo de aluguel!");
    }
  }

  public void devolverAcervo(Aluguel aluguel){
    File arq = new File(arquivo_devolvidos);

    try {
      FileWriter fw = new FileWriter(arq, true);
      fw.write(aluguel.toString());
      fw.write(System.lineSeparator());
      fw.close();
    } catch (Exception e) {
      System.out.println("Não foi possível criar o arquivo de aluguel!");
    }
  }

  public List<Aluguel> retornaListaAlugueis(){
    recuperarAlugueis();
    recuperarDevolvidos();
    return getListaAluguel();
  }

  private void recuperarAlugueis(){
    File arq = new File(arquivo_alugados);

    if(!arq.exists()){
      return;
    }

    limparListaAluguel();

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        int codigo = new Integer(strVt[0]);

        TipoMovimentacaoAcervoEnum tipoAluguel = TipoMovimentacaoAcervoEnum.valueOf(new Integer(strVt[1]));
        
        Usuario usuario = getUsuarioService().recuperarUsuario(new Integer(strVt[2]));

        Acervo acervo = getAcervoService().recuperarAcervo(new Integer(strVt[3]));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtEmprestimo = LocalDate.parse(strVt[4], dtf);

        LocalDate dtPrevistaDevolucao = LocalDate.parse(strVt[5], dtf);

        Aluguel aluguel = new Aluguel(codigo, tipoAluguel, usuario, acervo, dtEmprestimo, dtPrevistaDevolucao);

        getListaAluguel().add(aluguel);

      }

      sc.close();

    } catch (IOException e) {
      System.out.println("Não foi possível ler o arquivo de alugados!");
    } catch (NumberFormatException e) {
      System.out.println("Não foi possível transformar o valor para um número!");
    }
  }

  private void recuperarDevolvidos(){
    File arq = new File(arquivo_devolvidos);

    limparListaAluguel();

    try {
      Scanner sc = new Scanner(arq);
      
      while(sc.hasNext()){
        
        String str = sc.nextLine();

        String[] strVt = str.split(";");

        int codigo = new Integer(strVt[0]);

        TipoMovimentacaoAcervoEnum tipoAluguel = TipoMovimentacaoAcervoEnum.valueOf(new Integer(strVt[1]));
        
        Usuario usuario = getUsuarioService().recuperarUsuario(new Integer(strVt[2]));

        Acervo acervo = getAcervoService().recuperarAcervo(new Integer(strVt[3]));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtEmprestimo = LocalDate.parse(strVt[4], dtf);

        LocalDate dtPrevistaDevolucao = LocalDate.parse(strVt[5], dtf);
        LocalDate dtDevolucao = LocalDate.parse(strVt[6], dtf);

        Aluguel aluguel = new Aluguel(codigo, tipoAluguel, usuario, acervo, dtEmprestimo, dtPrevistaDevolucao, dtDevolucao);

        getListaAluguel().add(aluguel);

      }

      sc.close();

    } catch (IOException e) {
      System.out.println("Não foi possível ler o arquivo de devolvidos!");
    } catch (NumberFormatException e) {
      System.out.println("Não foi possível transformar o valor para um número!");
    }
  }

  public Aluguel verificarAcervoAlugado(int codigoAcervo){

    recuperarAlugueis();

    for (Aluguel aluguel : listaAluguel) {
      if(aluguel.getTipoAluguel().getCodigo() == TipoMovimentacaoAcervoEnum.ALUGADO.getCodigo()){
        if(aluguel.getAcervo().getCodigo() == codigoAcervo){
          return aluguel;
        }
      }
    }

    return null;
  }

  public Aluguel verificarAcervoDevolvido(int codigoAcervo){

    recuperarDevolvidos();

    for (Aluguel aluguel : listaAluguel) {
      if(aluguel.getTipoAluguel().getCodigo() == TipoMovimentacaoAcervoEnum.DEVOLVIDO.getCodigo()){
        if(aluguel.getAcervo().getCodigo() == codigoAcervo){
          return aluguel;
        }
      }
    }

    return null;
  }

  public int recuperarUltimoCodigoAluguel(){
    recuperarAlugueis();
    if(getListaAluguel().size() == 0){
      return 0;
    }

    return getListaAluguel().get(getListaAluguel().size() - 1).getCodigo();
  }

}
