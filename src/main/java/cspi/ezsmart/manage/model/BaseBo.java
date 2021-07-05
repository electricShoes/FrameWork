package cspi.ezsmart.manage.model;

import java.io.Serializable;




public class BaseBo
  implements Serializable
{
  private static final long serialVersionUID = 4160333140375324250L;
  private String regId;
  private String regDt;
  private String modId;
  private String modDt;
  
  public String getRegDt() {
    return this.regDt;
  }
  public void setRegDt(String insDt) {
    this.regDt = insDt;
  }
  public String getRegId() {
    return this.regId;
  }
  public void setRegId(String insId) {
    this.regId = insId;
  }
  public String getModDt() {
    return this.modDt;
  }
  public void setModDt(String updDt) {
    this.modDt = updDt;
  }
  public String getModId() {
    return this.modId;
  }
  public void setModId(String updId) {
    this.modId = updId;
  }
}
