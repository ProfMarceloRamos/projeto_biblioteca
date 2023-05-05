package enumeration;

public enum TipoRevistaEnum {
 
  FOFOCA(1,"Revista de Fofoca"),
  POLITICA(2,"Revista de Política"),
  RELIGIAO(3,"Revista de Religião"),
  INFORMATICA(4,"Revista de Informática"),
  REGIONAL(5, "Revista Regional");

  private String descricao;
  private int codigo;

  public String getDescricao() {
    return descricao;
  }

  public int getCodigo() {
    return codigo;
  }

  TipoRevistaEnum(String desc){
    descricao = desc;
  }

  TipoRevistaEnum(int codigo, String desc){
    this.codigo = codigo;
    descricao = desc;
  }

  public static void imprimeTiposRevistas(){
    for(TipoRevistaEnum tr : TipoRevistaEnum.values()){
      System.out.println(tr.codigo + " - " + tr.descricao);
    }
  }

  public static TipoRevistaEnum valueOf(int codigo){
    for(TipoRevistaEnum tr : TipoRevistaEnum.values()){
      if(tr.getCodigo() == codigo){
        return tr;
      }
    }
    return null;
  }

}
