package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.LangDao;
import cspi.ezsmart.manage.service.LangService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("langService")
public class LangServiceImpl
  implements LangService
{
  protected Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  LangDao langDao;

  
  public List<EzMap> getLang(EzMap param) throws Exception {
    return this.langDao.selectLang(param);
  }

  
  public List<EzMap> getList(Object param) throws Exception {
    return this.langDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.langDao.selectListCount(param);
  }

  
  public EzMap insertLang(EzMap param) throws Exception {
    return Utilities.getInsertResult(this.langDao.insertLang(param));
  }

  
  public EzMap updateLang(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.langDao.updateLang(param));
  }

  
  public EzMap deleteLang(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.langDao.deleteLang(param));
  }

  
  public EzMap deleteLangList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteLang(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }


  
  public EzMap saveLang(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertLang(param); 
    if ("u".equals(param.getString("stat")))
      return updateLang(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteLang(param);
    }
    return null;
  }

  
  public EzMap saveLang(List<EzMap> list) throws Exception {
    this.logger.debug("saveLang  다건 =======================");
    List<EzMap> result = new ArrayList<>();

    
    List<EzMap> langList = getLangGb();
    
    String gubnCd = null;
    
    for (int i = 0; i < list.size(); i++) {
      if ("c".equals(((EzMap)list.get(i)).getString("stat"))) {
        
        gubnCd = ((EzMap)list.get(i)).getString("gubnCd");
        if ("20".equals(gubnCd))
        {
          ((EzMap)list.get(i)).setString("langCd", langSeq());
        }

        
        for (int j = 0; j < langList.size(); j++) {
          
          ((EzMap)list.get(i)).setString("langGb", ((EzMap)langList.get(j)).getString("dtlCd"));
          
          result.add(saveLang(list.get(i)));
        } 
      } else if ("u".equals(((EzMap)list.get(i)).getString("stat"))) {
        result.add(saveLang(list.get(i)));
      } 
    } 
    
    return Utilities.getSaveResult(result);
  }






  
  private String langSeq() throws Exception {
    return this.langDao.langSeq();
  }






  
  private List<EzMap> getLangGb() throws Exception {
    return this.langDao.selectLangCd();
  }
}
