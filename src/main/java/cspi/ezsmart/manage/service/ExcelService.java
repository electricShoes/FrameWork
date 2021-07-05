package cspi.ezsmart.manage.service;

import org.springframework.web.multipart.MultipartRequest;

public interface ExcelService {
  Object excelToList(MultipartRequest paramMultipartRequest) throws Exception;
}
