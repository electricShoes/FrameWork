 package cspi.ezsmart.manage.dao;
 
 import cspi.ezsmart.common.dao.EzDao;
 import cspi.ezsmart.common.model.EzMap;
 import java.util.List;
 import org.springframework.stereotype.Component;
 
 @Component
 public class CorpDao  extends EzDao
 {
   public int getRowCount() throws Exception {
     return ((Integer)getData("ezsmart.base.ZzCorpM.selectRowCount")).intValue();
   }
 
   
   public List<EzMap> selectList(Object param) throws Exception {
     return getList("ezsmart.corp.selectList", param);
   }
   
   public int selectListCount(Object param) throws Exception {
     return ((Integer)getData("ezsmart.corp.selectListCount", param)).intValue();
   }
 
 
 
 
   
   public int insert(Object param) throws Exception {
     return ((Integer)getData("ezsmart.base.ZzCorpM.insert", param)).intValue();
   }
 
 
 
   
   public int update(Object param) throws Exception {
     return ((Integer)getData("ezsmart.base.ZzCorpM.update", param)).intValue();
   }
 
 
 
   
   public int delete(Object param) throws Exception {
     return delete("ezsmart.base.ZzCorpM.delete", param);
   }
   public int deleteCorp1(Object param) throws Exception {
     return delete("ezsmart.base.ZzCorpM.delete", param);
   }
 
 
 
 
   
   public int merge(Object param) throws Exception {
     return insert("ezsmart.base.ZzCorpM.merge", param);
   }
 }
