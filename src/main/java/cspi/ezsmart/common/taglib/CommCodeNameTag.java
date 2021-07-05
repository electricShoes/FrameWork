package cspi.ezsmart.common.taglib;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.service.CodeService;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class CommCodeNameTag  extends TagSupport
{
  private static final long serialVersionUID = 6129581713388769101L;
  private String rootCodeId = "";
  private String upCodeId;
  private String codeId;
  private int codeLevel;
  private String codeLang = "";
  
  private CodeService codeService = (CodeService)Utilities.getBean("codeService");
  public String getRootCodeId() {
    return this.rootCodeId;
  }
  public void setRootCodeId(String rootCodeId) {
    this.rootCodeId = rootCodeId;
  }
  public int getCodeLevel() {
    return this.codeLevel;
  }
  public void setCodeLevel(int codeLevel) {
    this.codeLevel = codeLevel;
  }
  public String getUpCodeId() {
    return this.upCodeId;
  }
  public void setUpCodeId(String upCodeId) {
    this.upCodeId = upCodeId;
  }
  public String getCodeId() {
    return this.codeId;
  }
  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }
  public String getCodeLang() {
    return this.codeLang;
  }
  public void setCodeLang(String codeLang) {
    this.codeLang = codeLang;
  }

  
  public int doStartTag() throws JspException {
    EzMap param = new EzMap();
    param.put("rootCodeId", getRootCodeId());
    param.put("upCodeId", getUpCodeId());
    param.put("codeLevel", Integer.valueOf(getCodeLevel()));
    param.put("codeId", getCodeId());
    param.put("codeLang", getCodeLang());
    try {
      EzMap code = this.codeService.getCode(param);
      if (code != null)
        this.pageContext.getOut().print(code.getString("codeName")); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return 0;
  }
}