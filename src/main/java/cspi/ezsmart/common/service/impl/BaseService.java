package cspi.ezsmart.common.service.impl;

import cspi.ezsmart.common.service.IBaseService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

@Service("baseService")
public class BaseService implements IBaseService {
  EgovMap egovMap;
}

