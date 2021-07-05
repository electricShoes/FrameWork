package cspi.ezsmart.common.service.impl;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.service.IBaseWebsocketService;
import cspi.ezsmart.common.util.Utilities;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

public abstract class BaseWebsocketService
  implements IBaseWebsocketService {
  private Map<Session, EgovMap> sessionMap = new HashMap<>();

  
  public Map<Session, EgovMap> getSessionMap() throws Exception {
    return this.sessionMap;
  }

  
  public EgovMap getSessionInfo(Session session) throws Exception {
    if (session == null)
      return null; 
    return this.sessionMap.get(session);
  }

  
  public void addWebsocket(Session session, EndpointConfig config) throws Exception {
    EgovMap map = new EgovMap();
    map.put("sessionId", session.getId());
    map.put("session", session);
    getSessionMap().put(session, map);
  }

  
  public void removeWebsocket(Session session, CloseReason reason) throws Exception {
    getSessionMap().remove(session);
  }



  
  public Object recieveMessage(Session session, String message) throws Exception {
    EgovMap pc = this.sessionMap.get(session);
    EgovMap ret = new EgovMap();
    ret.put("success", Boolean.valueOf(false));
    if (pc == null)
      return Utilities.getJsonString(ret); 
    EzMap ezMap = Utilities.getJson(message);
    if (ezMap == null)
      return Utilities.getJsonString(ret); 
    return socketProcess(session, pc, (Map)ezMap);
  }


  
  public void errorWebsocket(Session session, Throwable e) throws Exception {}


  
  public int sendMessage(Object message) throws Exception {
    int ret = 0;
    for (Session session : getSessionMap().keySet()) {
      if (sendMessage(session, message))
        ret++; 
    } 
    return ret;
  }



  
  public boolean sendHealthMessage(Session session, String text) throws Exception {
    try {
      if (Utilities.isEmpty(text))
        return false; 
      session.getBasicRemote().sendText(text);
      return true;
    } catch (Exception ex) {
      try {
        session.close();
      } catch (Exception exception) {}


      
      return false;
    } 
  }
  
  public boolean sendMessage(Session session, Object message) throws Exception {
    String text = Utilities.getJsonString(message);
    if (Utilities.isEmpty(text))
      return false; 
    try {
      session.getBasicRemote().sendText(text);
      return true;
    } catch (Exception exception) {

      
      return false;
    } 
  }
  
  public void healthCheck() throws Exception {
    EgovMap message = new EgovMap();
    message.put("pcId", Integer.valueOf(-1));
    message.put("eventId", Integer.valueOf(-1));
    message.put("message", "healthCheck");
    String text = Utilities.getJsonString(message);
    try {
      for (Session session : getSessionMap().keySet()) {
        sendHealthMessage(session, text);
      }
    } catch (Exception ex) {
      Utilities.trace(ex);
    } 
  }
  
  public int getConnetedCount() {
    return this.sessionMap.size();
  }
}
