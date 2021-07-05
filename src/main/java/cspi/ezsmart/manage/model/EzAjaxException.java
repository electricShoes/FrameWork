package cspi.ezsmart.manage.model;

import cspi.ezsmart.manage.model.EzException;



public class EzAjaxException
  extends EzException
{
  private static final long serialVersionUID = 7867419743700887725L;
  
  public EzAjaxException() {}
  
  public EzAjaxException(String message) {
    super(message);
  }
  
  public EzAjaxException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public EzAjaxException(Throwable cause) {
    super(cause);
  }




  
  protected EzAjaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
  
  public EzAjaxException(int errorCode) {
    super(errorCode);
  }
  
  public EzAjaxException(int errorCode, String message) {
    super(errorCode, message);
  }
  
  public EzAjaxException(int errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
  }
  
  public EzAjaxException(int errorCode, Throwable cause) {
    super(errorCode, cause);
  }





  
  protected EzAjaxException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(errorCode, message, cause, enableSuppression, writableStackTrace);
  }
}
