 package cspi.ezsmart.common.util;
 
 import com.fasterxml.jackson.databind.ObjectMapper;
 
 import cspi.ezsmart.common.model.AbstractTreeVo;
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.model.ITreeVo;
 import cspi.ezsmart.common.util.ExcelUtil;
 import cspi.ezsmart.common.util.MailUtil;
 import cspi.ezsmart.common.util.PKZip;
 import cspi.ezsmart.common.util.SessionUtil;
 import cspi.ezsmart.common.util.security.KisaSeed256;
 import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;
 import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 import java.awt.Dimension;
 import java.awt.image.BufferedImage;
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.UnsupportedEncodingException;
 import java.lang.reflect.Method;
 import java.net.HttpURLConnection;
 import java.net.InetAddress;
 import java.net.Socket;
 import java.net.URL;
 import java.net.URLDecoder;
 import java.net.URLEncoder;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Locale;
 import java.util.Map;
 import java.util.Random;
 import java.util.UUID;
 import javax.annotation.PostConstruct;
 import javax.annotation.Resource;
 import javax.imageio.ImageIO;
 import javax.servlet.ServletContext;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.axis.encoding.Base64;
 import org.apache.commons.beanutils.BeanUtils;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.json.simple.JSONObject;
 import org.json.simple.parser.JSONParser;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ConfigurableApplicationContext;
 import org.springframework.context.MessageSource;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.stereotype.Component;
 import org.springframework.util.FileCopyUtils;
 import org.springframework.web.context.request.RequestContextHolder;
 import org.springframework.web.context.request.ServletRequestAttributes;
 import org.springframework.web.servlet.i18n.SessionLocaleResolver;
 import org.w3c.dom.Document;
 
 @Component
 public class Utilities
 {
   private static final String _ENC_LANG = "UTF-8";
   private static ConfigurableApplicationContext context;
   private static ObjectMapper objectMapper;
   private static ServletContext servletContext;
   private static SessionLocaleResolver localeResolver;
   private static MessageSource messageSource;
   private static EgovPropertyServiceImpl propertiesService;
   private static final Log logger = LogFactory.getLog(cspi.ezsmart.common.util.Utilities.class);
   @Autowired
   private ConfigurableApplicationContext ctx;
   @Autowired
   ServletContext sCtx;
   @Autowired
   SessionLocaleResolver sessionLocaleResolver;
   @Autowired
   MessageSource localMessageSource;
   @Resource(name = "propertiesService")
   EgovPropertyServiceImpl egovProperties;
   
   @PostConstruct
   public void init() {
     context = this.ctx;
     objectMapper = (ObjectMapper)context.getBean("jacksonObjectMapper");
     servletContext = this.sCtx;
     localeResolver = this.sessionLocaleResolver;
     messageSource = this.localMessageSource;
     propertiesService = this.egovProperties;
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static String getArrayString(String[] arr) {
     return getArrayString(arr, ",");
   }
 
 
 
 
 
 
 
   
   public static String getArrayString(String[] arr, String strSpliter) {
     if (arr == null || arr.length == 0) return ""; 
     String strRet = "";
     for (int i = 0; i < arr.length; i++) {
       if (strRet.length() > 0) strRet = String.valueOf(strRet) + strSpliter; 
       strRet = String.valueOf(strRet) + arr[i];
     } 
     return strRet;
   }
 
 
 
 
 
   
   public static void clearDirectory(String strDirectory) {
     clearDirectory(new File(strDirectory));
   }
 
 
 
 
 
 
   
   public static void clearDirectory(File fDirectory) {
     if (fDirectory == null)
       return;  if (!fDirectory.isDirectory())
       return; 
     File[] listFile = fDirectory.listFiles();
     int nLength = listFile.length;
     
     for (int i = 0; i < nLength; i++) {
       File fSub = listFile[i];
       if (fSub.isFile()) { fSub.delete(); }
       else if (fSub.isDirectory()) { deleteFile(fSub); }
     
     } 
   }
 
 
 
 
 
   
   public static void deleteDirectory(File fDirectory) {
     if (fDirectory == null || !fDirectory.isDirectory())
       return;  deleteFile(fDirectory);
   }
 
 
 
 
 
   
   public static void deleteDirectory(String strDirectory) {
     deleteDirectory(new File(strDirectory));
   }
 
 
 
 
 
 
   
   public static void deleteFile(File fDelete) {
     if (fDelete == null)
       return;  clearDirectory(fDelete);
     fDelete.delete();
   }
 
 
 
 
 
 
   
   public static boolean createDirectory(String strDirectory) {
     File dInfo = new File(strDirectory);
     dInfo.mkdirs();
     return dInfo.exists();
   }
 
 
 
 
 
 
   
   public static String getByteString(byte[] bt) {
     if (bt == null) return ""; 
     return new String(bt);
   }
 
 
 
 
 
   
   public static String getDateString() {
     return getDateString("");
   }
 
 
 
 
 
 
   
   public static String getDateString(Date dt) {
     return getDateString(dt, "");
   }
 
 
 
 
 
 
   
   public static String getDateString(String strSpliter) {
     return getDateString(new Date(), strSpliter);
   }
 
 
 
 
 
 
 
   
   public static String getDateString(Date dt, String strSpliter) {
     String strFormat = "yyyy" + strSpliter + "MM" + strSpliter + "dd";
     SimpleDateFormat sf = new SimpleDateFormat(strFormat);
     return sf.format(dt);
   }
 
 
 
 
 
 
   
   public static String getDateFormat(String strDate) {
     return getDateFormat(strDate, "/");
   }
 
 
 
 
 
 
 
   
   public static String getDateFormat(String strDate, String spliter) {
     if (isEmpty(strDate)) return null; 
     strDate = getOnlyNumberString(strDate);
     if (strDate.length() < 8) return strDate; 
     return String.valueOf(strDate.substring(0, 4)) + spliter + strDate.substring(4, 6) + spliter + strDate.substring(6, 8);
   }
 
 
 
 
 
 
   
   public static String getTimeFormat(String strDate) {
     return getTimeFormat(strDate, ":");
   }
 
 
 
 
 
 
 
   
   public static String getTimeFormat(String strDate, String spliter) {
     if (isEmpty(strDate)) return null; 
     strDate = getOnlyNumberString(strDate);
     if (strDate.length() < 6) return strDate; 
     return String.valueOf(strDate.substring(0, 2)) + spliter + strDate.substring(2, 4) + spliter + strDate.substring(4, 6);
   }
 
 
 
 
 
 
   
   public static String getDateTimeFormat(String strDateTime) {
     return getDateTimeFormat(strDateTime, "/", ":");
   }
 
 
 
 
 
 
 
 
   
   public static String getDateTimeFormat(String strDateTime, String spliterD, String spliterT) {
     if (isEmpty(strDateTime)) return null; 
     strDateTime = getOnlyNumberString(strDateTime);
     
     if (strDateTime.length() < 14) return strDateTime; 
     return String.valueOf(getDateFormat(strDateTime, spliterD)) + " " + getTimeFormat(strDateTime.substring(8));
   }
 
 
 
 
 
 
   
   public static Date parseDate(String strDate) {
     if (strDate == null || strDate.length() == 0) return null; 
     return parseDate(strDate, "-", ":", " ");
   }
 
 
 
 
 
 
 
 
   
   public static Date parseDate(String strDate, String spliter) {
     if (strDate == null || strDate.length() == 0) return null; 
     return parseDate(strDate, spliter, spliter, spliter);
   }
 
 
 
 
 
 
 
 
 
 
   
   public static Date parseDate(String strDate, String spliterD, String spliterT, String separator) {
     if (strDate == null || strDate.length() == 0) return null; 
     String strFormat = "yyyy" + spliterD + "MM" + spliterD + "dd" + separator + "HH" + spliterT + "mm" + spliterT + "ss";
     return parseDate(strDate, new SimpleDateFormat(strFormat));
   }
 
 
 
 
 
 
 
 
   
   public static Date parseDate(String strDate, SimpleDateFormat format) {
     try {
       return format.parse(strDate);
     } catch (Exception ex) {
       return null;
     } 
   }
 
 
 
 
 
 
   
   public static String getSizeString(long nSize) {
     int K = 1000;
     int M = K * K;
     int G = M * K;
     if (nSize < K) return (new StringBuilder(String.valueOf(nSize))).toString(); 
     if (nSize < M)
       return String.valueOf(parseInt((new StringBuilder(String.valueOf(nSize / K))).toString())) + "." + parseLong((new StringBuilder(String.valueOf(nSize % K * 100L / K))).toString()) + " K"; 
     if (nSize < G) {
       return String.valueOf(parseInt((new StringBuilder(String.valueOf(nSize / M))).toString())) + "." + parseLong((new StringBuilder(String.valueOf(nSize % M * 100L / M))).toString()) + " M";
     }
     return String.valueOf(parseInt((new StringBuilder(String.valueOf(nSize / G))).toString())) + "." + parseLong((new StringBuilder(String.valueOf(nSize % G * 100L / G))).toString()) + " M";
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static String getFilePath(String fullPath) {
     if (fullPath == null) {
       return null;
     }
     fullPath = fullPath.replace('\\', '/');
     int index = fullPath.lastIndexOf("/");
     if (index == -1) {
       index = fullPath.lastIndexOf("\\");
       if (index > -1) {
         return fullPath.substring(0, index);
       }
     } 
     
     return fullPath.substring(0, index);
   }
 
 
 
 
 
 
   
   public static String getFileName(String filepath) {
     if (filepath == null) {
       return null;
     }
     int index = filepath.lastIndexOf("/");
     if (index > -1) {
       return filepath.substring(index + 1);
     }
     index = filepath.lastIndexOf("\\");
     if (index > -1) {
       return filepath.substring(index + 1);
     }
     
     return filepath;
   }
 
 
 
 
 
 
   
   public static String getFileNameWithoutExtension(String filepath) {
     String filename = getFileName(filepath);
     if (filename == null || filename.length() == 0) return "";
     
     int pos = filename.lastIndexOf(".");
     if (pos <= 0) return filename;
     
     return filename.substring(0, pos);
   }
 
 
 
 
 
 
 
   
   public static String trimStart(String strSource, String strTrim) {
     if (strTrim == null || strTrim.length() == 0) return strSource; 
     while (strSource.startsWith(strTrim)) {
       strSource = strSource.substring(strTrim.length());
     }
     return strSource;
   }
 
 
 
 
 
 
 
 
   
   public static String trimEnd(String strSource, String strTrim) {
     if (strTrim == null || strTrim.length() == 0) return strSource; 
     if (strSource == null || strSource.length() == 0) return ""; 
     while (strSource.endsWith(strTrim)) {
       int nIndex = strSource.length() - strTrim.length();
       strSource = strSource.substring(0, nIndex);
     } 
     return strSource;
   }
 
 
 
 
 
 
 
 
 
   
   public static String padLeft(Object source, int nSize, char szPad) {
     String strSource = (source == null) ? "" : source.toString();
     
     int nCnt = nSize - strSource.length();
     if (nCnt < 1) return strSource; 
     for (int i = 0; i < nCnt; i++) {
       strSource = String.valueOf(szPad) + strSource;
     }
     
     return strSource;
   }
 
 
 
 
 
 
 
 
   
   public static String padRight(Object source, int nSize, char szPad) {
     String strSource = (source == null) ? "" : source.toString();
     
     int nCnt = nSize - strSource.length();
     if (nCnt < 1) return strSource; 
     for (int i = 0; i < nCnt; i++) {
       strSource = String.valueOf(strSource) + szPad;
     }
     return strSource;
   }
 
 
 
 
 
 
 
   
   public static String getNumberString(long nNumber, int nSize) {
     return getNumberString(nNumber, nSize, '0');
   }
 
 
 
 
 
 
 
 
   
   public static String getNumberString(long nNumber, int nSize, char chPad) {
     String strNumber = (new StringBuilder(String.valueOf(nNumber))).toString();
     return padLeft(strNumber, nSize, chPad);
   }
 
 
 
 
 
 
   
   public static String getScriptText(String strValue) {
     if (strValue == null) return ""; 
     return strValue.replace("\"", "\\\"").replace("\r\n", "\n").replace("\n", "\\n");
   }
 
 
 
 
 
 
 
 
 
   
   public static byte[] getStringByte(String strText) throws Exception {
     if (strText == null || strText.length() == 0) return new byte[0]; 
     return strText.getBytes("UTF-8");
   }
 
 
 
 
 
 
   
   public static String getTimeString(Object time) {
     return getTimeString(parseLong(time));
   }
 
 
 
 
 
 
   
   public static String getTimeString(long time) {
     long totalSec = time / 1000L;
     long hour = totalSec / 3600L;
     long min = totalSec % 3600L / 60L;
     long sec = totalSec % 3600L % 60L;
     return String.valueOf(padLeft(Long.valueOf(hour), 2, '0')) + ":" + padLeft(Long.valueOf(min), 2, '0') + ":" + padLeft(Long.valueOf(sec), 2, '0');
   }
 
 
 
 
 
 
   
   public static String getDateTimeString(Date dt) {
     if (dt == null) return null; 
     return String.valueOf(getDateString(dt, "/")) + " " + getTimeString(dt, ":");
   }
 
 
 
 
 
   
   public static String getDateTimeString() {
     return String.valueOf(getDateString()) + " " + getTimeString();
   }
 
 
 
 
 
   
   public static String getTimeString() {
     return getTimeString(new Date(), ":");
   }
 
 
 
 
 
 
   
   public static String getTimeString(String strSpliter) {
     return getTimeString(new Date(), strSpliter);
   }
 
 
 
 
 
 
 
 
   
   public static String getTimeString(Date dt, String strSpliter) {
     try {
       String strFormat = "HH" + strSpliter + "mm" + strSpliter + "ss";
       SimpleDateFormat sf = new SimpleDateFormat(strFormat);
       return sf.format(dt);
     } catch (Exception ex) {
       trace(ex);
       
       return "";
     } 
   }
 
 
 
 
   
   public static String getPKCd() {
     return getPKCd(Long.toHexString(System.currentTimeMillis()).toUpperCase());
   }
 
 
 
 
 
 
   
   public static String getPKCd(String prefix) {
     int length = (prefix == null) ? 20 : (20 - prefix.length());
     if (length < 0) return prefix.substring(0, 20); 
     return String.valueOf(nullCheck(prefix)) + getUniqID(length);
   }
 
 
 
 
 
 
   
   public static String getUniqID(int nLength) {
     if (nLength < 1) return ""; 
     Random rd = new Random();
     char chAdded = 'A';
     char chRange = '\032';
     char[] chValue = new char[nLength];
     for (int i = 0; i < nLength; i++) {
       chValue[i] = (char)(int)(rd.nextDouble() * 26.0D + 65.0D);
     }
     
     return (new String(chValue)).toUpperCase();
   }
 
 
 
 
 
 
   
   public static String getUniqNumberID(int nLength) {
     if (nLength < 1) return ""; 
     Random rd = new Random();
     
     char chAdded = '0';
     char chRange = '\t';
     char[] chValue = new char[nLength];
     for (int i = 0; i < nLength; i++) {
       //chValue[i] = (char)(int)(rd.nextDouble() * 9.0D + 48.0D + ((i == 0) ? true : false));
     }
     return new String(chValue);
   }
 
 
 
 
 
   
   public static String getUUID() {
     return String.valueOf(UUID.randomUUID());
   }
 
 
 
 
 
 
 
   
   public static boolean isWritableDirectory(String strPath) {
     File dir = new File(strPath);
     return (dir.isDirectory() && dir.canWrite());
   }
 
 
 
 
 
 
   
   public static String nullCheck(String strValue) {
     return (strValue == null) ? "" : strValue;
   }
 
 
 
 
 
 
   
   public static String nullCheck(Object obj) {
     return (obj == null) ? "" : obj.toString();
   }
 
 
 
 
 
 
   
   public static boolean parseBoolean(String strValue) {
     try {
       if (isEmpty(strValue)) return false; 
       return Boolean.parseBoolean(strValue);
     } catch (Exception e) {
       return false;
     } 
   }
 
 
 
 
 
 
   
   public static boolean parseBoolean(Object obj) {
     try {
       if (isEmpty(obj)) return false; 
       return parseBoolean(obj.toString());
     } catch (Exception e) {
       return false;
     } 
   }
 
 
 
 
 
 
   
   public static double parseDouble(String strValue) {
     try {
       return Double.parseDouble(strValue);
     } catch (Exception e) {
       return 0.0D;
     } 
   }
 
 
 
 
 
 
   
   public static double parseDouble(Object obj) {
     try {
       if (obj == null) return 0.0D; 
       return parseDouble(obj.toString());
     } catch (Exception e) {
       return 0.0D;
     } 
   }
 
 
 
 
 
 
   
   public static float parseFloat(String strValue) {
     try {
       return Float.parseFloat(strValue);
     } catch (Exception e) {
       return 0.0F;
     } 
   }
 
 
 
 
 
 
   
   public static float parseFloat(Object obj) {
     try {
       if (isEmpty(obj)) return 0.0F; 
       return parseFloat(obj.toString());
     } catch (Exception e) {
       return 0.0F;
     } 
   }
 
 
 
 
 
 
   
   public static int parseInt(Object obj) {
     try {
       if (isEmpty(obj)) return 0; 
       return (int)Double.parseDouble(obj.toString());
     } catch (Exception e) {
       return 0;
     } 
   }
 
 
 
 
 
 
   
   public static byte parseByte(Object obj) {
     try {
       if (isEmpty(obj)) return 0; 
       return (byte)(int)Double.parseDouble(obj.toString());
     } catch (Exception e) {
       return 0;
     } 
   }
 
 
 
 
 
 
 
   
   public static long parseLong(String strValue) {
     try {
       return Long.parseLong(strValue);
     } catch (Exception e) {
       return 0L;
     } 
   }
 
 
 
 
 
 
   
   public static long parseLong(Object obj) {
     try {
       if (obj == null) return 0L; 
       return parseLong(obj.toString());
     } catch (Exception e) {
       traceWarning(e);
       return 0L;
     } 
   }
 
 
   
   public static boolean saveXML(String strFileName, Document xDoc) {
     return true;
   }
 
 
 
 
 
 
 
 
 
   
   public static void trace(Exception ex) {
     logger.error(ex.getMessage(), ex);
   }
 
 
 
 
 
   
   public static void trace(Object obj) {
     trace(obj.toString());
   }
 
 
 
 
 
   
   public static void trace(String strLog) {
     logger.info(strLog);
   }
 
 
 
 
 
   
   public static void traceWarning(Exception e) {
     logger.debug(e.getMessage(), e);
   }
 
 
 
 
 
   
   public static void traceWarning(String msg) {
     logger.debug(msg);
   }
 
 
 
 
 
 
   
   public static int Ceiling(double dbNumber) {
     int nNumber = (int)dbNumber;
     if (nNumber < dbNumber) nNumber++; 
     return nNumber;
   }
 
 
 
 
 
 
   
   public static String getEncodeText(String strText) {
     if (strText == null || strText.length() == 0) return ""; 
     String strRet = "";
     try {
       strRet = URLEncoder.encode(strText, "UTF-8");
     }
     catch (Exception e) {
       strRet = "";
     } 
     return strRet;
   }
 
 
 
 
 
 
   
   public static String getDecodeText(String strText) {
     if (strText == null || strText.length() == 0) return ""; 
     String strRet = "";
     try {
       strText = strText.replace("_amp_", "&");
       strText = strText.replace("_dpy_", "%");
       
       strRet = URLDecoder.decode(strText, "UTF-8");
     }
     catch (Exception e) {
       strRet = "";
     } 
     return strRet;
   }
 
 
 
 
 
 
 
 
 
   
   public static Date getDate(int nYear, int nMonth, int nDay) {
     Calendar cd = Calendar.getInstance();
     cd.set(11, 0);
     cd.set(12, 0);
     cd.set(13, 0);
     cd.set(14, 0);
     cd.set(1, nYear);
     cd.set(2, nMonth - 1);
     cd.set(5, nDay);
     return cd.getTime();
   }
 
 
 
 
 
   
   public static Date getCurrentTime() {
     return getCalendar().getTime();
   }
 
 
 
 
 
   
   public static Calendar getCalendar() {
     Calendar cd = Calendar.getInstance();
     return cd;
   }
 
 
 
 
 
   
   public static int getYear() {
     return getCalendar().get(1);
   }
 
 
 
 
 
   
   public static int getMonth() {
     return getCalendar().get(2) + 1;
   }
 
 
 
 
 
   
   public static int getDay() {
     return getCalendar().get(5);
   }
 
 
 
 
 
 
 
 
   
   public static Calendar getCalendar(int nYear, int nMonth, int nDay) {
     Calendar cd = Calendar.getInstance();
     cd.set(1, nYear);
     cd.set(2, nMonth - 1);
     cd.set(5, nYear);
     return cd;
   }
 
 
 
 
 
 
 
   
   public static String getFileExtension(String filename) {
     if (filename == null) {
       return null;
     }
     int index = filename.lastIndexOf(".");
     if (index > -1) {
       return filename.substring(index + 1);
     }
     return "";
   }
 
 
 
 
 
 
   
   public static List<Map<String, Object>> convert2CamelCase(List<Map<String, Object>> list) {
     List<Map<String, Object>> ret = new ArrayList<>();
     for (int i = 0; i < list.size(); i++) {
       ret.add(convert2CamelCase(list.get(i)));
     }
     return ret;
   }
 
 
 
 
 
 
   
   public static Map<String, Object> convert2CamelCase(Map<String, Object> map) {
     Map<String, Object> ret = new HashMap<>();
     for (String key : map.keySet()) {
       ret.put(convert2CamelCase(key), map.get(key));
     }
     return ret;
   }
 
 
 
 
 
 
 
   
   public static String convert2CamelCase(String underScore) {
     if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
       return underScore;
     }
     StringBuilder result = new StringBuilder();
     boolean nextUpper = false;
     int len = underScore.length();
     
     for (int i = 0; i < len; i++) {
       char currentChar = underScore.charAt(i);
       if (currentChar == '_') {
         nextUpper = true;
       }
       else if (nextUpper) {
         result.append(Character.toUpperCase(currentChar));
         nextUpper = false;
       } else {
         result.append(Character.toLowerCase(currentChar));
       } 
     } 
     
     return result.toString();
   }
   
   public static List<ITreeVo> makeHierarchy(List<? extends ITreeVo> list, EzMap itemMap) {
     return AbstractTreeVo.makeHierarchy(list, itemMap);
   }
 
   
   public static List<ITreeVo> makeHierarchy(List<? extends ITreeVo> list) {
     return AbstractTreeVo.makeHierarchy(list);
   }
 
 
 
 
 
 
 
 
   
   public static String wget(String strUri, String strPost) {
     InputStreamReader isr = null;
     OutputStreamWriter osw = null;
     
     try {
       URL url = new URL(strUri);
       
       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
       if (conn == null) return ""; 
       conn.setDoOutput(true);
       conn.setConnectTimeout(10000);
       conn.setUseCaches(false);
       if (strPost != null) conn.setRequestMethod("POST"); 
       conn.setRequestProperty("Content-Type", "text/plain");
       if (strPost != null && strPost.length() > 0) {
         osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
         osw.write(strPost);
         osw.flush();
       } 
       if (conn.getResponseCode() != 200) return "";
       
       isr = new InputStreamReader(conn.getInputStream());
       BufferedReader br = new BufferedReader(isr);
       
       char[] buffer = new char[1024];
       StringBuffer sb = new StringBuffer();
       while (true) {
         int nRead = br.read(buffer);
         if (nRead <= 0)
           break; 
         sb.append(buffer, 0, nRead);
       } 
       
       return sb.toString();
     } catch (Exception ex) {
       
       return "";
     } finally {
       try {
         if (isr != null) isr.close(); 
       } catch (Exception exception) {}
       
       try {
         if (osw != null) osw.close(); 
       } catch (Exception exception) {}
     } 
   }
 
 
 
 
 
 
 
   
   public static String getServerIp() {
     try {
       return InetAddress.getLocalHost().getHostAddress();
     } catch (Exception e) {
       return null;
     } 
   }
 
 
 
 
 
   
   public static String getPeerIp() {
     String ip = null;
     
     if (getRequest() != null) {
       ip = getRequest().getHeader("X-Forwarded-For");
       
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("WL-Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("HTTP_CLIENT_IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("HTTP_X_FORWARDED_FOR");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("X-Real-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("X-RealIP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getHeader("REMOTE_ADDR");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = getRequest().getRemoteAddr().replaceAll("0:0:0:0:0:0:0:1", "127.0.0.1");
       }
     } 
     
     return ip;
   }
 
 
 
 
 
   
   public static HttpSession getSession() {
     if (getRequest() != null) return getRequest().getSession(); 
     return null;
   }
 
 
 
 
 
   
   public static String getSessionId() {
     String sessionId = (getSession() == null) ? null : (String)getSession().getAttribute("timsSessionId");
     if (isEmpty(sessionId)) {
       sessionId = getUniqID(20);
       setSessionId(sessionId);
     } 
     return sessionId;
   }
 
 
 
 
 
 
   
   public static void setSessionId(String sessionId) {
     if (getSession() != null) getSession().setAttribute("timsSessionId", sessionId);
   
   }
 
 
 
 
 
 
   
   public static double round(double number, int num) {
     double pow = Math.pow(10.0D, num);
     double tmp = number * pow;
     double value = Math.round(tmp);
     return value / pow;
   }
 
 
 
 
 
 
 
   
   public static double round(float number, int num) {
     double pow = Math.pow(10.0D, num);
     double tmp = number * pow;
     long value = Math.round(tmp);
     return value / pow;
   }
 
 
 
 
 
 
 
   
   public static String roundString(double number, int num) {
     double value = round(number, num);
     return getNumberString(value, num);
   }
 
 
 
 
 
 
 
   
   public static String roundString(float number, int num) {
     double value = round(number, num);
     return getNumberString(value, num);
   }
 
 
 
 
 
 
   
   public static String getMoneyString(long nNumber) {
     return getNumberString(nNumber);
   }
 
 
 
 
 
 
   
   public static String getNumberString(long nNumber) {
     String strMoney = "";
     while (nNumber > 0L) {
       if (strMoney.length() > 0) strMoney = "," + strMoney; 
       strMoney = String.valueOf(getNumberString(nNumber % 1000L, 3)) + strMoney;
       nNumber /= 1000L;
       if (nNumber < 1L)
         break; 
     }  strMoney = trimStart(strMoney, "0");
     if (strMoney.length() == 0) strMoney = "0"; 
     return strMoney;
   }
 
 
 
 
 
 
 
   
   public static String getNumberString(double number, int num) {
     long nVal = (long)number;
     String n = getNumberString(nVal);
     if (num < 1) return n; 
     String format = String.format("%" + num + "f", new Object[] { Double.valueOf(number) });
     int nIndex = format.indexOf(".");
     
     String dbVal = "";
     if (nIndex > -1) dbVal = format.substring(nIndex + 1);
     
     String zero = "";
     
     for (int i = 0; i < num; i++) {
       zero = String.valueOf(zero) + "0";
     }
     String db = String.valueOf(dbVal) + zero;
     db = db.substring(0, num);
     return String.valueOf(n) + "." + db;
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadFile(HttpServletResponse response, String strFileName, String strDisplayName, long start, long end) {
     return DownloadFile(response, strFileName, strDisplayName, start, end, null);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadFile(HttpServletResponse response, String strFileName, String strDisplayName, long start, long end, String contentsType) {
     if (response == null) return false; 
     File fDownload = new File(strFileName);
     if (!fDownload.isFile()) return false; 
     int nLength = (int)(end - start + 1L);
     response.setStatus(206);
     if (isEmpty(contentsType)) contentsType = "application/octet-stream"; 
     try {
       response.setContentType(contentsType);
       response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(strDisplayName, "UTF-8") + 
           "\";");
       response.setContentLength(nLength);
       return DownloadStream((OutputStream)response.getOutputStream(), fDownload, start, end);
     } catch (Exception ex) {
       return false;
     } 
   }
 
 
 
 
 
 
 
 
   
   public static boolean DownloadFile(String strFileName, String strDisplayName) {
     return DownloadFile(getResponse(), strFileName, strDisplayName, null);
   }
 
 
 
 
 
 
 
 
   
   public static boolean DownloadFile(HttpServletResponse response, String strFileName, String strDisplayName) {
     return DownloadFile(response, strFileName, strDisplayName, null);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadFile(HttpServletResponse response, String strFileName, String strDisplayName, String contentsType) {
     if (response == null) return false; 
     File fDownload = new File(strFileName);
     logger.debug("file  Path :" + strFileName);
     if (!fDownload.isFile()) return false; 
     int nLength = (int)fDownload.length();
     if (isEmpty(contentsType)) contentsType = "application/octet-stream"; 
     try {
       response.setContentType(contentsType);
       response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(strDisplayName, "UTF-8") + 
           "\";");
       response.setContentLength(nLength);
       return DownloadStream((OutputStream)response.getOutputStream(), fDownload);
     } catch (Exception ex) {
       try {
         response.sendError(404);
       } catch (Exception e) {
         traceWarning(e);
       } 
       return false;
     } 
   }
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadText(HttpServletResponse response, String text, String strDisplayName) {
     if (response == null) return false; 
     int nLength = text.length();
     
     try {
       response.setContentType("application/octet-stream");
       response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(strDisplayName, "UTF-8") + 
           "\";");
       response.setContentLength(nLength);
       return DownloadStream((OutputStream)response.getOutputStream(), text);
     } catch (Exception ex) {
       try {
         response.sendError(404);
       } catch (Exception e) {
         traceWarning(e);
       } 
       return false;
     } 
   }
 
 
 
 
 
 
 
 
   
   public static boolean DownloadStream(OutputStream out, String text) {
     if (out == null || text == null) return false;
     
     BufferedOutputStream outs = null;
     try {
       outs = new BufferedOutputStream(out);
       try {
         outs.write(text.getBytes("UTF-8"), 0, text.length());
       }
       catch (Exception e) {
         return false;
       } 
       return true;
     } catch (Exception ex) {
       logger.error("file download error", ex);
       return false;
     } finally {
       try {
         if (outs != null) outs.close();
       
       } catch (Exception exception) {}
     } 
   }
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadStream(OutputStream out, File fDownload) throws Exception {
     if (out == null || fDownload == null) return false; 
     if (!fDownload.isFile()) return false; 
     return DownloadStream(out, new FileInputStream(fDownload));
   }
 
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadStream(OutputStream out, File fDownload, long start, long end) throws Exception {
     if (out == null || fDownload == null) return false; 
     if (!fDownload.isFile()) return false; 
     return DownloadStream(out, new FileInputStream(fDownload), start, end);
   }
 
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadStream(OutputStream out, InputStream is, long start, long end) {
     if (out == null || is == null) return false;
     
     long size = end - start + 1L;
     long left = size;
     BufferedInputStream fin = null;
     BufferedOutputStream outs = null;
     try {
       fin = new BufferedInputStream(is);
       if (start > 0L) fin.skip(start); 
       outs = new BufferedOutputStream(out);
       byte[] buffer = new byte[4096];
       try {
         int nRead = fin.read(buffer);
         while (nRead != -1 && left > 0L) {
           int write = (int)((left > nRead) ? nRead : left);
           outs.write(buffer, 0, write);
           left -= nRead;
           nRead = fin.read(buffer);
         } 
       } catch (Exception e) {
         logger.error("file download error", e);
         return false;
       } 
       return true;
     } catch (Exception ex) {
       logger.error("file download error", ex);
       return false;
     } finally {
       try {
         if (fin != null) fin.close();
       
       } catch (Exception exception) {}
 
       
       try {
         if (outs != null) outs.close();
       
       } catch (Exception exception) {}
     } 
   }
 
 
 
 
 
 
 
 
 
   
   public static boolean DownloadStream(OutputStream out, InputStream is) {
     if (out == null || is == null) return false;
     
     BufferedInputStream fin = null;
     BufferedOutputStream outs = null;
     try {
       fin = new BufferedInputStream(is);
       outs = new BufferedOutputStream(out);
       byte[] buffer = new byte[4096];
       try {
         int nRead = fin.read(buffer);
         while (nRead != -1) {
           outs.write(buffer, 0, nRead);
           nRead = fin.read(buffer);
         } 
       } catch (Exception e) {
         logger.error("file download error", e);
         return false;
       } 
       return true;
     } catch (Exception ex) {
       logger.error("file download error", ex);
       return false;
     } finally {
       try {
         if (fin != null) fin.close();
       
       } catch (Exception exception) {}
 
       
       try {
         if (outs != null) outs.close();
       
       } catch (Exception exception) {}
     } 
   }
 
 
 
 
 
 
 
 
 
   
   public static String readText(File file) throws Exception {
     FileReader reader = new FileReader(file);
     return FileCopyUtils.copyToString(reader);
   }
 
 
 
 
 
 
 
   
   public static void writeFile(File file, byte[] data) throws Exception {
     FileCopyUtils.copy(data, file);
   }
 
 
 
 
 
 
   
   public static boolean isNotEmpty(Object obj) {
     return !isEmpty(obj);
   }
 
 
 
 
 
 
 
 
   
   public static boolean isEmpty(Object obj) {
     if (obj == null) return true; 
     if (obj instanceof String)
       return ((String)obj).isEmpty(); 
     if (obj instanceof Map)
       return ((Map)obj).isEmpty(); 
     if (obj instanceof Collection)
       return ((Collection)obj).isEmpty(); 
     if (obj instanceof Object[])
       return (((Object[])obj).length == 0); 
     if (obj instanceof File)
       return !((File)obj).exists(); 
     if (obj instanceof List) {
       return (((List)obj).size() == 0);
     }
     try {
       Method method = obj.getClass().getMethod("isEmpty", null);
       return ((Boolean)method.invoke(obj, new Object[0])).booleanValue();
     } catch (Exception ex) {
       return false;
     } 
   }
 
 
 
 
 
   
   public static StackTraceElement getSourceInfo() {
     try {
       return Thread.currentThread().getStackTrace()[2];
     }
     catch (Exception ex) {
       return null;
     } 
   }
 
 
 
 
 
   
   public static HttpServletRequest getRequest() {
     try {
       ServletRequestAttributes servletContainer = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
       if (servletContainer != null) return servletContainer.getRequest(); 
       return null;
     } catch (Exception ex) {
       return null;
     } 
   }
 
 
 
 
 
 
   
   public static HttpServletResponse getResponse() {
     HttpServletRequest request = getRequest();
     if (request == null) return null;
     
     return (HttpServletResponse)request.getAttribute("httpServletResponse");
   }
 
 
 
 
 
 
 
   
   public static Dimension getImageSize(String fileName) {
     try {
       BufferedImage bimg = ImageIO.read(new File(fileName));
       int width = bimg.getWidth();
       int height = bimg.getHeight();
       return new Dimension(width, height);
     } catch (Exception e) {
       return null;
     } 
   }
 
 
 
 
 
 
   
   public static List<String> renameFilenames(List<String> list) {
     Map<String, String> map = new HashMap<>();
     List<String> sList = new ArrayList<>();
     for (int i = 0; i < list.size(); i++) {
       String name = list.get(i);
       if (map.containsKey(name)) {
         for (int j = 1; j < 100000; ) {
           String name2 = String.valueOf(name) + "(" + j + ")";
           if (map.containsKey(name2)) {
             j++; continue;
           }  map.put(name2, name);
           sList.add(name2);
           
           break;
         } 
       } else {
         map.put(name, name);
         sList.add(name);
       } 
     } 
     return sList;
   }
 
 
 
 
 
 
   
   public static String getCookieValue(String cookieName) {
     if (isEmpty(cookieName)) return null; 
     Cookie[] cookies = getRequest().getCookies();
     for (int i = 0; cookies != null && i < cookies.length; i++) {
       if (cookieName.equals(cookies[i].getName())) return cookies[i].getValue(); 
     } 
     return null;
   }
 
 
 
 
 
 
   
   public static Cookie getCookie(String cookieName) {
     if (isEmpty(cookieName)) return null; 
     Cookie[] cookies = getRequest().getCookies();
     for (int i = 0; cookies != null && i < cookies.length; i++) {
       if (cookieName.equals(cookies[i].getName())) return cookies[i]; 
     } 
     return null;
   }
 
 
 
 
 
 
   
   public static void setCookie(String name, String value) {
     HttpServletResponse response = getResponse();
     value = (value == null) ? null : value.replace("\n", "").replace("\r", "");
     Cookie cookie = new Cookie(name, value);
     cookie.setSecure(true);
     cookie.setPath("/");
     
     response.addCookie(cookie);
   }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   
   public static <T> T getBean(Class<?> cls) {
     try {
       return (T)context.getBean(cls);
     } catch (Exception ex) {
       return null;
     } 
   }
 
 
 
 
 
 
 
   
   public static <T> T getBean(String name) {
     try {
       return (T)context.getBean(name);
     } catch (Exception ex) {
       return null;
     } 
   }
 
 
 
 
 
 
 
   
   public static List<String> getFiles(String path, List<String> list) {
     File fPath = new File(path);
     if (!fPath.isDirectory()) return null; 
     File[] fList = fPath.listFiles();
     for (int i = 0; i < fList.length; i++) {
       File file = fList[i];
       if (file.isFile()) list.add(file.getPath()); 
       if (file.isDirectory()) {
         getFiles(file.getPath(), list);
       }
     } 
     return list;
   }
 
 
 
 
 
 
   
   public static List<String> getFiles(String path) {
     return getFiles(path, new ArrayList<>());
   }
 
 
 
 
 
 
   
   public static boolean isMobile() {
     try {
       HttpServletRequest request = getRequest();
       String userAgent = request.getHeader("user-agent");
       if (isEmpty(userAgent)) return false; 
       boolean mobile1 = userAgent.matches(".*(iPad|iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
       boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
       if (mobile1 || mobile2) {
         return true;
       }
       return false;
     } catch (Exception ex) {
       return false;
     } 
   }
 
 
 
 
 
 
   
   public static byte[] hexToByteArray(String hex) {
     if (hex == null || hex.length() % 2 != 0) {
       return new byte[0];
     }
     
     byte[] bytes = new byte[hex.length() / 2];
     for (int i = 0; i < hex.length(); i += 2) {
       byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
       bytes[(int)Math.floor((i / 2))] = value;
     } 
     return bytes;
   }
 
 
 
 
 
 
 
   
   public static void writeFile(String fileName, String content) throws Exception {
     FileOutputStream fileOutputStream = null;
     BufferedWriter out = null;
     try {
       fileOutputStream = new FileOutputStream(fileName);
       OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "EUC-KR");
       out = new BufferedWriter(OutputStreamWriter);
       if (isNotEmpty(content)) out.write(content);
     
     } catch (Exception ex) {
       traceWarning(ex);
     } finally {
       try {
         if (out != null) out.close(); 
       } catch (Exception ex) {
         traceWarning(ex);
       } 
     } 
   }
 
 
 
 
 
 
 
 
   
   public static String makeParamText(String contents, Map<String, String> map) {
     if (map == null) return contents; 
     if (isEmpty(contents)) return contents;
     
     for (Map.Entry<String, String> elem : map.entrySet()) {
       String name = "#{" + (String)elem.getKey() + "}";
       String value = nullCheck(elem.getValue());
       contents = contents.replace(name, value);
     } 
     return contents;
   }
 
 
 
 
 
 
 
   
   public static String getOnlyNumberString(Object str) {
     if (str == null) return null; 
     if (isEmpty(str)) return str.toString(); 
     return str.toString().replaceAll("[^0-9]", "");
   }
 
 
 
 
 
 
   
   public static String getPhoneNumberFormat(Object phoneNumber) {
     if (isEmpty(phoneNumber)) return nullCheck(phoneNumber); 
     String numbers = getOnlyNumberString(phoneNumber);
     if (numbers.length() == 10)
       return String.valueOf(numbers.substring(0, 3)) + "-" + numbers.substring(3, 6) + "-" + numbers.substring(6); 
     if (numbers.length() >= 11) {
       return String.valueOf(numbers.substring(0, 3)) + "-" + numbers.substring(3, 7) + "-" + numbers.substring(7);
     }
     return nullCheck(phoneNumber);
   }
 
 
 
 
 
 
 
   
   public static String getWebPathToRealPath(String webPath) {
     return servletContext.getRealPath(webPath);
   }
 
 
 
 
 
 
   
   public static String getRealPath(String path) {
     return servletContext.getRealPath(path);
   }
 
 
 
 
 
   
   public static String getLangCd() {
     String langCd = "KO";
     HttpSession session = getSession();
     if (session == null) return langCd; 
     langCd = (String)session.getAttribute("langCd");
     if (isEmpty(langCd)) langCd = "KO"; 
     return langCd;
   }
   
   public static String passwordEncode(String pwd) {
     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     return encoder.encode(pwd);
   }
   
   public static boolean passwordMatch(String pwd, String encodedPwd) {
     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     return encoder.matches(pwd, encodedPwd);
   }
 
   
   public static String encrypt(String text) throws Exception {
     return KisaSeed256.encrypt(text);
   }
 
 
 
 
 
   
   public static String decrypt(String text) throws Exception {
     return KisaSeed256.decrypt(text);
   }
 
 
   
   public static <T> T beanToBean(Object obj, Class cls) {
     return mapToBean(beanToMap(obj), cls);
   }
 
   
   public static boolean beanToBean(Object target, Object src) {
     try {
       Map<String, Object> map = beanToMap(src);
       mapToBean(map, target);
       return true;
     } catch (Exception ex) {
       return false;
     } 
   }
 
   
   public static <T> T mapToBean(Map map, Class cls) {
     return (T)objectMapper.convertValue(map, cls);
   }
 
 
   
   public static List<Map<String, Object>> convertMapList(List list) {
     if (list == null)
       return null; 
     List<Map<String, Object>> arr = new ArrayList<>();
     for (int i = 0; i < list.size(); i++) {
       arr.add(beanToMap(list.get(i)));
     }
     return arr;
   }
   
   public static Map<String, Object> beanToMap(Object obj) {
     if (obj == null)
       return null; 
     return (Map<String, Object>)objectMapper.convertValue(obj, EzMap.class);
   }
 
   
   public static boolean beanToMap(Map target, Object obj) {
     if (target == null)
       return false; 
     EzMap map = (EzMap)objectMapper.convertValue(obj, EzMap.class);
     target.putAll((Map)map);
     return true;
   }
 
   
   public static boolean mapToBean(Map map, Object bean) {
     if (bean == null || map == null)
       return false; 
     if (map == null || bean == null)
       return false; 
     try {
       BeanUtils.populate(bean, map);
       return true;
     } catch (Exception ex) {
       return false;
     } 
   }
   
   public static List<Map<String, Object>> beanToMap(List<?> list) {
     List<Map<String, Object>> l = new ArrayList<>();
     for (int i = 0; i < list.size(); i++) {
       l.add(beanToMap(list.get(i)));
     }
     return l;
   }
 
   
   public static String encodeBase64(byte[] value) {
     return Base64.encode(value);
   }
   
   public static String encodeBase64(String value) throws Exception {
     return encodeBase64(value.getBytes());
   }
   
   public static String decodeBase64(String value) {
     try {
       int offset = value.length() % 4;
       String off = "";
       for (int i = 0; i < offset; i++) {
         off = String.valueOf(off) + "=";
       }
       value = String.valueOf(value) + off;
       return new String(Base64.decode(value), "UTF-8");
     } catch (UnsupportedEncodingException e) {
       return null;
     } 
   }
 
   
   public static byte[] decodeBase64Byte(String value) {
     return Base64.decode(value);
   }
   
   public static <T> T parseJson(String json, Class<T> clz) throws Exception {
     try {
       return (T)objectMapper.readValue(json, clz);
     } catch (Exception ex) {
       return null;
     } 
   }
 
   
   public static EzMap getJson(String json) throws Exception {
     return parseJson(json, EzMap.class);
   }
 
   
   public static String getJsonString(Object obj) throws Exception {
     if (obj == null)
       return null; 
     if (obj instanceof String)
       return (String)obj; 
     return objectMapper.writeValueAsString(obj);
   }
   
   public static String getJsonPString(Object obj, String callback) throws Exception {
     if (isEmpty(callback)) {
       return objectMapper.writeValueAsString(obj);
     }
     return String.valueOf(callback) + "(" + objectMapper.writeValueAsString(obj) + ")";
   }
 
   
//   public static boolean zip(String zipName, List<File> targets, List<String> dispNames) {
//     return PKZip.zip(zipName, targets, dispNames);
//   }
// 
//   public static boolean zip(String zipName, String target) {
//     return PKZip.zip(zipName, target);
//   }
// 
//   public static boolean zip(String zipName, List<String> targets) {
//     return PKZip.zip(zipName, targets);
//   }
// 
//   public static boolean unZip(String strZipfilename, String strTargetDir) {
//     return PKZip.unZip(strZipfilename, strTargetDir);
//   }
// 
//   public static List<List<Object>> excelToList(File file) throws Exception {
//     return ExcelUtil.excelToList(file);
//   }
   
   public static Locale getLocale() {
     HttpServletRequest request = getRequest();
     if (request != null) {
       return localeResolver.resolveLocale(request);
     }
     return new Locale("ko");
   }
 
   
   public static String getLocaleLang() {
     return getLocale().getLanguage();
   }
   
   public static String getMessage(String code) {
     return getMessage(code, null, getLocale());
   }
   
   public static String getMessage(String code, Object[] arg) {
     return getMessage(code, arg, getLocale());
   }
   
   public static String getMessage(String code, Locale locale) {
     return getMessage(code, null, locale);
   }
   
   public static String getMessage(String code, Object[] arg, Locale locale) {
     return messageSource.getMessage(code, arg, locale);
   }
   
   public static String getProperty(String name) {
     return propertiesService.getString(name, "");
   }
   
   public static int getPropertyInt(String name) {
     return propertiesService.getInt(name, 0);
   }
   
   public static long getPropertyLong(String name) {
     return propertiesService.getLong(name, 0L);
   }
   
   public static double getPropertyDouble(String name) {
     return propertiesService.getDouble(name, 0.0D);
   }
   
   public static double getPropertyFloat(String name) {
     return propertiesService.getFloat(name, 0.0F);
   }
   
   public static boolean getPropertyBoolean(String name) {
     return propertiesService.getBoolean(name, false);
   }
   
   public static EzMap getLoginUser() {
     HttpSession session = getSession();
     if (session == null)
       return null; 
     return (EzMap)session.getAttribute("LOGIN_USER");
   }
 
   
   public static String getLoginUserId() {
     EzMap loginUser = getLoginUser();
     if (loginUser == null)
       return null; 
     return loginUser.getString("userId");
   }
 
   
   public static boolean isLogin() {
     return isNotEmpty(getLoginUserId());
   }
 
 
 
   
   public static EzMap getGridData(List<? extends Object> list, int totalRecordCount, int currentPageNo, int pageSize, int recordCountPerPage) {
     PaginationInfo page = new PaginationInfo();
     page.setCurrentPageNo(currentPageNo);
     page.setTotalRecordCount(totalRecordCount);
     page.setPageSize(pageSize);
     page.setRecordCountPerPage(recordCountPerPage);
     return getGridData(list, page);
   }
   
   public static EzMap getGridData(List<? extends Object> list, PaginationInfo page) {
     EzMap ezMap = new EzMap();
     ezMap.put("list", list);
     ezMap.put("pagination", page);
     return ezMap;
   }
   
   public static EzMap getInsertResult(int count) {
     EzMap ret = new EzMap();
     ret.put("insert", Integer.valueOf(count));
     return ret;
   }
   
   public static EzMap getUpdateResult(int count) {
     EzMap ret = new EzMap();
     ret.put("insert", Integer.valueOf(count));
     return ret;
   }
   
   public static EzMap getDeleteResult(int count) {
     EzMap ret = new EzMap();
     ret.put("delete", Integer.valueOf(count));
     return ret;
   }
   
   public static EzMap addResult(EzMap src, EzMap result) {
     if (src == null || result == null)
       return src; 
     int insert = src.getInt("insert") + result.getInt("insert");
     int update = src.getInt("update") + result.getInt("update");
     int delete = src.getInt("delete") + result.getInt("delete");
     src.put("insert", Integer.valueOf(insert));
     src.put("update", Integer.valueOf(update));
     src.put("delete", Integer.valueOf(delete));
     src.put("change", Integer.valueOf(insert + update + delete));
     return src;
   }
   
   public static EzMap getSaveResult(List<EzMap> resultList) {
     EzMap result = new EzMap();
     result.put("resultList", resultList);
     for (int i = 0; i < resultList.size(); i++) {
       addResult(result, resultList.get(i));
     }
     return result;
   }
   
   public static String getFileId() {
     return String.valueOf(getDateString("")) + getTimeString("") + getUniqNumberID(6);
   }
   
   public static boolean isAjaxRequest() {
     return SessionUtil.isAjaxRequest();
   }
   
   public static boolean sendMail(String emailTo, String subject, String body) throws Exception {
     return MailUtil.sendMail(emailTo, subject, body, null, null);
   }
 
 
 
   
   public static boolean sendMail(String emailTo, String subject, String body, List<String> attFiles, List<String> dispnames) throws Exception {
     return MailUtil.sendMail(emailTo, subject, body, 
         attFiles, dispnames);
   }
 
   public static List<List<Object>> excelToList(InputStream is, boolean b2007) throws Exception {
//     return ExcelUtil.excelToList(is, b2007);
	   return null;
   }
 
   public static JSONObject getSocketTransFileToJson(String ip, int port, String div, File file) {
     JSONObject jsonObj = null;
     Socket socket = null;
 
     
     int responseCode = 1;
     
     BufferedOutputStream bos = null;
     BufferedInputStream bis = null;
     BufferedReader br = null;
     int recv = 0;
     
     try { socket = new Socket(ip, port);
       logger.debug("서버 연결 완료");
 
       
       bos = new BufferedOutputStream(socket.getOutputStream());
       bos.write((new StringBuilder(String.valueOf(div))).toString().getBytes("utf-8"));
       bos.flush();
       
       br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       StringBuilder sb = new StringBuilder();
       
       recv = br.read();
       
       if (recv != 1) {
         logger.debug("코드 전송 실패");
         jsonObj = new JSONObject();
         jsonObj.put("error", "code");
         return jsonObj;
       } 
       logger.debug("코드 전송 완료");
 
 
       
       bis = new BufferedInputStream(new FileInputStream(file));
       
       String fileSize = (new StringBuilder(String.valueOf((int)file.length()))).toString();
       bos.write(fileSize.getBytes("utf-8"));
       bos.flush();
 
       
       recv = br.read();
       
       if (recv != 1) {
         logger.debug("파일 사이즈 전송 실패");
         jsonObj = new JSONObject();
         jsonObj.put("error", "file");
         return jsonObj;
       } 
 
       
       int len_sum = 0;
       int size = 1024;
       byte[] data = new byte[size];
       
       int len;
       while ((len = bis.read(data)) != -1) {
         bos.write(data, 0, len);
         len_sum += len;
       } 
       
       bos.flush();
       
       recv = br.read();
       
       if (recv != 1) {
         jsonObj = new JSONObject();
         jsonObj.put("error", "file");
         return jsonObj;
       } 
       logger.debug("파일 전송 완료");
 
       
       sb.setLength(0);
       while (true) {
         String temp = br.readLine();
         if (temp == null)
           break;  sb.append(temp);
       } 
       String res = sb.toString();
       logger.debug("json 수신 완료");
       socket.close();
       JSONParser jsonParser = new JSONParser();
       jsonObj = (JSONObject)jsonParser.parse(res.toString()); }
     
     catch (Exception e)
     { e.printStackTrace();
       jsonObj = new JSONObject();
       jsonObj.put("error", "error");
       return jsonObj; }
     finally
     { 
       try { if (bos != null) bos.close(); 
         if (bis != null) bis.close();  }
       catch (Exception e)
       { e.printStackTrace();
         jsonObj = new JSONObject();
         jsonObj.put("error", "error");
         return jsonObj; }  }  try { if (bos != null) bos.close();  if (bis != null) bis.close();  } catch (Exception e) { e.printStackTrace(); jsonObj = new JSONObject(); jsonObj.put("error", "error"); return jsonObj; }
 
     
     return jsonObj;
   }
 }
