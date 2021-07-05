package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.CorpDao;
import cspi.ezsmart.manage.service.CorpService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("corpService")
public class CorpServiceImpl
  implements CorpService
{
  @Autowired
  CorpDao corpDao;
  
  public List<EzMap> getList(Object param) throws Exception {
    return this.corpDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.corpDao.selectListCount(param);
  }

  
  public EzMap deleteCorp1(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.corpDao.delete(param));
  }
  
  public EzMap deleteCorp(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteCorp1(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveCorp(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.corpDao.merge(param));
    return ret;
  }
}
