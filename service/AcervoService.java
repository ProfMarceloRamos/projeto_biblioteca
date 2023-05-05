package service;

import java.util.List;

import entidade.Acervo;
import repository.AcervoRepository;

public class AcervoService {

  private AcervoRepository repository;

  private AcervoRepository getRepository() {
    if(repository == null){
      repository = new AcervoRepository();
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

}
