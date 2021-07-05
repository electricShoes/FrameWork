package cspi.ezsmart.common.controller;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.manage.service.CodeService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping({"/commCode", "0101040000", "{menuId}/commCode"})
public class CommCodeController
{
  protected Logger logger = LoggerFactory.getLogger(getClass());
  
  @Resource(name = "codeService")
  CodeService codeService;
  
  @RequestMapping({"getComboCode"})
  @ResponseBody
  public Object getCode(@RequestBody EzMap param) throws Exception {
    this.logger.debug("[getCode] param :: {}", param);
    
    return this.codeService.getComboCode(param);
  }
}

