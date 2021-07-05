package cspi.ezsmart.manage.dao;

import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class FileDao
  extends EzDao
{
  public List<EzMap> selectList(Object param) throws Exception {
    return getList("ezsmart.file.selectList", param);
  }

  
  public EzMap selectFileInfo(Object param) throws Exception {
    return (EzMap)getData("ezsmart.file.selectFileInfo", param);
  }

  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.file.selectListCount", param)).intValue();
  }

  
  public int insertFileInfo(Object param) throws Exception {
    return insert("ezsmart.zzFileInfoM.insertFileInfo", param);
  }

  
  public int updateFileInfo(Object param) throws Exception {
    return update("ezsmart.zzFileInfoM.updateFileInfo", param);
  }

  
  public int deleteFileInfo(Object param) throws Exception {
    return delete("ezsmart.zzFileInfoM.deleteFileInfo", param);
  }
}
