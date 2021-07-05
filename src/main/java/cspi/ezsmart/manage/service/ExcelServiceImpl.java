package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.service.ExcelService;
import java.io.InputStream;
import java.util.Iterator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@Service("excelService")
public class ExcelServiceImpl
  implements ExcelService
{
  public Object excelToList(MultipartRequest mrequest) throws Exception {
    Iterator<String> names = mrequest.getFileNames();
    if (names.hasNext()) {
      String name = names.next();
      MultipartFile file = mrequest.getFile(name);
      InputStream is = file.getInputStream();
      System.out.println("is + " + is);
      boolean b2007 = "xls".equals(Utilities.getFileExtension(file.getOriginalFilename()).toLowerCase());
      System.out.println("xls 여부 ==== " + b2007);
      return Utilities.excelToList(is, b2007);
    } 
    return null;
  }
}
