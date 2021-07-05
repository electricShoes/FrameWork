package cspi.ezsmart.manage.dao;
 
import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;
 
 
 
@Component
public class CodeDao extends EzDao{
	public List<EzMap> selectCodeCombo(Object param) throws Exception {
		return getList("ezsmart.code.ZzCodeD.selectCodeCombo", param);
	}
   
   public List<EzMap> selectList(Object param) throws Exception {
     return getList("ezsmart.code.ZzCodeM.selectList", param);
   }
 
   
   public List<EzMap> getListDetail(Object param) throws Exception {
     return getList("ezsmart.code.ZzCodeD.selectList", param);
   }
   
   public EzMap selectCode(Object param) throws Exception {
     return (EzMap)getData("ezsmart.code.ZzCodeM.selectCode", param);
   }
   
   public int selectListCount(Object param) throws Exception {
     return ((Integer)getData("ezsmart.code.ZzCodeM.selectListCount", param)).intValue();
   }
   
   public int insertCode(Object param) throws Exception {
     return insert("ezsmart.code.ZzCodeM.insert", param);
   }
   
   public int insertCodeDetail(Object param) throws Exception {
     return insert("ezsmart.code.ZzCodeD.insert", param);
   }
   
   public int updateCode(Object param) throws Exception {
     return update("ezsmart.code.ZzCodeM.update", param);
   }
   
   public int deleteCode(Object param) throws Exception {
     return delete("ezsmart.code.ZzCodeM.delete", param);
   }
   
   public int updateCodeDetail(Object param) throws Exception {
     return update("ezsmart.code.ZzCodeD.update", param);
   }
   
   public int deleteCodeDetail(Object param) throws Exception {
     return delete("ezsmart.code.ZzCodeD.delete", param);
   }
 }

