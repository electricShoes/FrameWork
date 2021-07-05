 package cspi.ezsmart.manage.dao;
 
 import cspi.ezsmart.common.dao.EzDao;
 import cspi.ezsmart.common.model.EzMap;
 import java.util.List;
 import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class DicDao
   extends EzDao
 {
   public List<EzMap> selectList(Object param) throws Exception {
     return getList("ezsmart.dic.selectList", param);
   }
   
   public int selectListCount(Object param) throws Exception {
     return ((Integer)getData("ezsmart.dic.selectListCount", param)).intValue();
   }
 
 
   
   public Object selectVar(EzMap param) {
     return null;
   }
 
   
   public int insertVar(EzMap param) throws Exception {
     return insert("ezsmart.dic.ZzDicM.insert", param);
   }
 
   
   public int updateVar(EzMap param) throws Exception {
     return update("ezsmart.dic.ZzDicM.update", param);
   }
 
   
   public int deleteVar(EzMap param) throws Exception {
     return delete("ezsmart.dic.ZzDicM.delete", param);
   }
 
   
   public int deleteVariable(EzMap param) throws Exception {
     return delete("ezsmart.dic.ZzDicM.delete", param);
   }
 }
