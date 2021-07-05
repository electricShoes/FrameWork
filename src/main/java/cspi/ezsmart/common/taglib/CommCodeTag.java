package cspi.ezsmart.common.taglib;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.service.CodeService;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class CommCodeTag
  extends TagSupport
{
  private static final long serialVersionUID = 6129581713388769101L;
  private String name = "";
  private String mstCd;
  private String dltCd;
  private boolean hasSelectTag = true;
  private String others = "";
  private String prefixLabel = "";
  private String prefixValue = "";
  private String selectedValue = "";
  private String className = "";
  private String refCd1;
  private String refCd2;
  private String refCd3;
  private String refCd4;
  private String refCd5;
  private String refNum1;
  private String refNum2;
  private String refNum3;
  private String refNum4;
  private String refNum5;
  private CodeService codeService = (CodeService)Utilities.getBean("codeService");
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getMstCd() {
    return this.mstCd;
  }
  
  public void setMstCd(String mstCd) {
    this.mstCd = mstCd;
  }
  
  public String getDltCd() {
    return this.dltCd;
  }
  
  public void setDltCd(String dltCd) {
    this.dltCd = dltCd;
  }
  
  public boolean isHasSelectTag() {
    return this.hasSelectTag;
  }
  
  public void setHasSelectTag(boolean hasSelectTag) {
    this.hasSelectTag = hasSelectTag;
  }
  
  public String getOthers() {
    return this.others;
  }
  
  public void setOthers(String others) {
    this.others = others;
  }
  
  public String getPrefixLabel() {
    return this.prefixLabel;
  }
  
  public void setPrefixLabel(String prefixLabel) {
    this.prefixLabel = prefixLabel;
  }
  
  public String getPrefixValue() {
    return this.prefixValue;
  }
  
  public void setPrefixValue(String prefixValue) {
    this.prefixValue = prefixValue;
  }
  
  public String getSelectedValue() {
    return this.selectedValue;
  }
  
  public void setSelectedValue(String selectedValue) {
    this.selectedValue = selectedValue;
  }
  
  public String getRefCd1() {
    return this.refCd1;
  }
  
  public void setRefCd1(String refCd1) {
    this.refCd1 = refCd1;
  }
  
  public String getRefCd2() {
    return this.refCd2;
  }
  
  public void setRefCd2(String refCd2) {
    this.refCd2 = refCd2;
  }
  
  public String getRefCd3() {
    return this.refCd3;
  }
  
  public void setRefCd3(String refCd3) {
    this.refCd3 = refCd3;
  }
  
  public String getRefCd4() {
    return this.refCd4;
  }
  
  public void setRefCd4(String refCd4) {
    this.refCd4 = refCd4;
  }
  
  public String getRefCd5() {
    return this.refCd5;
  }
  
  public void setRefCd5(String refCd5) {
    this.refCd5 = refCd5;
  }
  
  public String getRefNum1() {
    return this.refNum1;
  }
  
  public void setRefNum1(String refNum1) {
    this.refNum1 = refNum1;
  }
  
  public String getRefNum2() {
    return this.refNum2;
  }
  
  public void setRefNum2(String refNum2) {
    this.refNum2 = refNum2;
  }
  
  public String getRefNum3() {
    return this.refNum3;
  }
  
  public void setRefNum3(String refNum3) {
    this.refNum3 = refNum3;
  }
  
  public String getRefNum4() {
    return this.refNum4;
  }
  
  public void setRefNum4(String refNum4) {
    this.refNum4 = refNum4;
  }
  
  public String getRefNum5() {
    return this.refNum5;
  }
  
  public void setRefNum5(String refNum5) {
    this.refNum5 = refNum5;
  }
  
  public CodeService getCodeService() {
    return this.codeService;
  }
  
  public void setCodeService(CodeService codeService) {
    this.codeService = codeService;
  }
  
  public static long getSerialversionuid() {
    return 6129581713388769101L;
  }
  public String getClassName() {
    return this.className;
  }
  public void setClassName(String className) {
    this.className = className;
  }
  
  public int doStartTag() throws JspException {
    String options = "";
    
    EzMap param = new EzMap();
    param.put("mstCd", getMstCd());
    param.put("dltCd", getDltCd());
    param.put("refCd1", getRefCd1());
    param.put("refCd2", getRefCd2());
    param.put("refCd3", getRefCd3());
    param.put("refCd4", getRefCd4());
    param.put("refCd5", getRefCd5());
    param.put("refNum1", getRefNum1());
    param.put("refNum2", getRefNum2());
    param.put("refNum3", getRefNum3());
    param.put("refNum4", getRefNum4());
    param.put("refNum5", getRefNum5());
    
    try {
      List<EzMap> list = this.codeService.getComboCode(param);
      if (Utilities.isNotEmpty(this.prefixValue) || Utilities.isNotEmpty(this.prefixLabel)) {
        this.prefixValue = Utilities.nullCheck(this.prefixValue);
        this.prefixLabel = Utilities.nullCheck(this.prefixLabel);
        if (Utilities.isEmpty(this.prefixLabel))
          this.prefixLabel = this.prefixValue; 
        options = String.valueOf(options) + "<option value=\"" + this.prefixValue + "\">" + this.prefixLabel + "</options>";
      } 
      for (int i = 0; i < list.size(); i++) {
        EzMap code = list.get(i);
        String selected = "";
        if (code.getString("mstCd").equals(this.selectedValue)) {
          selected = " selected ";
        }
        options = String.valueOf(options) + "<option " + selected + " value=\"" + code.getString("dtlCd") + "\">" + code.getString("dtlNm") + "</options>";
      } 
      StringBuffer sel = new StringBuffer();
      if (this.hasSelectTag) {
        
        sel.append("<select ");
        if (Utilities.isNotEmpty(this.id))
          sel.append("id=\"" + this.id + "\" "); 
        if (Utilities.isNotEmpty(this.name))
          sel.append("name=\"" + this.name + "\" "); 
        if (Utilities.isNotEmpty(this.className))
          sel.append("class=\"" + this.className + "\" "); 
        if (Utilities.isNotEmpty(this.others))
          sel.append(this.others); 
        sel.append(">");
      } 
      sel.append(options);
      if (this.hasSelectTag)
        sel.append("</select>"); 
      this.pageContext.getOut().print(sel.toString());
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return 0;
  }
}
