package cspi.ezsmart.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EzLoggingAspect
{
  private final Logger logger = LoggerFactory.getLogger(cspi.ezsmart.common.aop.EzLoggingAspect.class);
  
  private long thresholdInMills = 3000L;
  
  public void setThresholdInMills(long thresholdInMills) {
    this.thresholdInMills = thresholdInMills;
  }
  
  public Object logProfiling(ProceedingJoinPoint pjp) throws Throwable {
    String targetClassName = pjp.getTarget().getClass().getName();
    String targetMethodName = pjp.getSignature().getName();
    StringBuffer logMsg = null;
    
    if (this.logger.isDebugEnabled()) {
      logMsg = new StringBuffer();
      logMsg.append("\n>>> " + targetClassName + "." + targetMethodName + "() Startup \n");
      Object[] args = pjp.getArgs();
      for (int i = 0; i < args.length; i++) {
        logMsg.append("    args[" + i + "] = " + args[i] + "\n");
      }
    } 
    long before = System.currentTimeMillis();
    Object retVal = pjp.proceed();
    long after = System.currentTimeMillis();
    
    long processingTime = after - before;
    if (this.logger.isDebugEnabled()) {
      logMsg.append("<<< " + targetClassName + "." + targetMethodName + "() Finish  (duration = " + processingTime + "ms)");
//      this.logger.debug((String)logMsg);
      this.logger.debug(logMsg.toString());
    } 
    if (processingTime > this.thresholdInMills) {
      this.logger.warn(" ▶▶▶  " + targetClassName + "." + targetMethodName + "() exeuted for  " + processingTime + "ms. (threshold:" + (this.thresholdInMills / 1000L) + "sec.)");
    }
    
    return retVal;
  }
  
  public void logException(Throwable ex) throws Throwable {
    StringBuffer msg = new StringBuffer();
    msg.append(" ▶▶▶▶▶▶ ERROR !! \n");
    
    this.logger.error(msg.toString());
    
    throw ex;
  }
}
