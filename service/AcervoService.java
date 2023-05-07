package service;

import java.util.List;

import entidade.Acervo;
import repository.AcervoRepositorio;

public class AcervoService {

  private AcervoRepositorio repository;

  private AcervoRepositorio getRepository() {
    if(repository == null){
      repository = new AcervoRepositorio();
    }
    return repository;
  }
  
  public void criarAcervo(Acervo acr){
    getRepository().criarAcervo(acr);
  }

  public int recuperarUltimoCodigoAcervo(){
    return getRepository().recuperarUltimoCodigoAcervo();
  }

  public List<Acervo> retornaListaAcervo(){
    return getRepository().retornaListaAcervo();
  }

  public Acervo recuperarAcervo(int codigo){
    return getRepository().recuperarAcervo(codigo);
  }

}
