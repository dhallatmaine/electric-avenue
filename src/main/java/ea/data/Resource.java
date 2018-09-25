package ea.data;

public class Resource {

  private Integer type;
  private Integer price;

  public static final Integer COAL = 1;
  public static final Integer OIL = 2;
  public static final Integer TRASH = 3;
  public static final Integer URANIUM = 4;
  public static final Integer HYBRID = 5;
  public static final Integer GREEN = 6;

  public static final String SCOAL = "COAL";
  public static final String SOIL = "OIL";
  public static final String STRASH = "TRSAH";
  public static final String SURANIUM = "URANIUM";
  public static final String SHYBRID = "HYBRID";
  public static final String SGREEN = "GREEN";

  public Resource() { }

  public Resource(Integer type, Integer price) {
    this.type = type;
    this.price = price;
  }

  public Integer getType() { return type; }
  public void setType(Integer type) { this.type = type; }

  public Integer getPrice() { return price; }
  public void setPrice(Integer price) { this.price = price; }

}