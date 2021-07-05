package cspi.ezsmart.manage.dao;

import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class LangDao
  extends EzDao
{
  public List<EzMap> selectList(Object param) throws Exception {
    System.out.println("2222222222222222222===============");
    return getList("ezsmart.langM.selectList", param);
  }
  
  public List<EzMap> selectLang(Object param) throws Exception {
    return getList("ezsmart.langM.selectLangM", param);
  }
  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.langM.selectListCount", param)).intValue();
  }
  
  public int insertLang(Object param) throws Exception {
    System.out.println("============랭다오");
    return insert("ezsmart.ZzLangM.insertLangM", param);
  }
  
  public int updateLang(Object param) throws Exception {
    return update("ezsmart.ZzLangM.updateLangM", param);
  }
  
  public int deleteLang(Object param) throws Exception {
    return delete("ezsmart.ZzLangM.deleteLangM", param);
  }
  
  public String langSeq() throws Exception {
    return (String)getData("ezsmart.langM.selectLangSeq", "");
  }
  public List<EzMap> selectLangCd() throws Exception {
    return getList("ezsmart.langM.selectLangCd");
  }
}
