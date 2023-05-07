package service;

import java.time.format.DateTimeFormatter;

import entidade.Aluguel;
import repository.AluguelRepositorio;

public class AluguelService {
  
  private AluguelRepositorio repositorio;

  public AluguelRepositorio getRepositorio() {
    if(repositorio == null){
      repositorio = new AluguelRepositorio();
    }
    return repositorio;
  }

  public void alugarAcervo(Aluguel aluguel){
    
    Aluguel alugado = getRepositorio().verificarAcervoAlugado(aluguel.getAcervo().getCodigo());

    if(alugado != null){

      String dtPevistaDevolucao = alugado.getDtPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

      System.out.printf("Não foi possível alugar o %s, pois o mesmo está alugado e tem previsão para ser entregue no dia %s\n",
      alugado.getAcervo().getNome(),dtPevistaDevolucao);

      return;
    }

    getRepositorio().alugarAcervo(aluguel);

  }

  public int recuperarUltimoCodigoAluguel(){
    return getRepositorio().recuperarUltimoCodigoAluguel();
  }

}
