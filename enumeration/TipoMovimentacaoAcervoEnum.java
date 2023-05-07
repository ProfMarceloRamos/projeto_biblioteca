package enumeration;

public enum TipoMovimentacaoAcervoEnum {
  
  ALUGADO(1, "Alugado"),
  DEVOLVIDO(2, "Devolvido");

  private int codigo;

  private String descricao;

  TipoMovimentacaoAcervoEnum(int codigo, String descricao){
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public static TipoMovimentacaoAcervoEnum valueOf(int codigo){
    for(TipoMovimentacaoAcervoEnum tr : TipoMovimentacaoAcervoEnum.values()){
      if(tr.getCodigo() == codigo){
        return tr;
      }
    }
    return null;
  }

}
