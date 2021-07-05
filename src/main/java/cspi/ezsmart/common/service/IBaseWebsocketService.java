package cspi.ezsmart.common.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import java.util.Map;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public interface IBaseWebsocketService {
  Map<Session, EgovMap> getSessionMap() throws Exception;
  
  void addWebsocket(Session paramSession, EndpointConfig paramEndpointConfig) throws Exception;
  
  void removeWebsocket(Session paramSession, CloseReason paramCloseReason) throws Exception;
  
  Object recieveMessage(Session paramSession, String paramString) throws Exception;
  
  int sendMessage(Object paramObject) throws Exception;
  
  void errorWebsocket(Session paramSession, Throwable paramThrowable) throws Exception;
  
  boolean sendMessage(Session paramSession, Object paramObject) throws Exception;
  
  String socketProcess(Session paramSession, EgovMap paramEgovMap, Map<String, Object> paramMap) throws Exception;
  
  EgovMap getSessionInfo(Session paramSession) throws Exception;
  
  void healthCheck() throws Exception;
  
  boolean sendHealthMessage(Session paramSession, String paramString) throws Exception;
  
  int getConnetedCount();
}

