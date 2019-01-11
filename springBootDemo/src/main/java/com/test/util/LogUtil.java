package com.test.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {

	public void writeLog(String paramString, Object paramObject)
	  {
	    Log localLog = LogFactory.getLog(paramString);
	    if ((paramObject instanceof Exception)) {
	      localLog.error(paramString, (Exception)paramObject);
	    } else {
	      localLog.error(paramObject);
	    }
	  }
	
	public void writeLog(String paramString)
	  {
	    Log localLog = LogFactory.getLog(paramString);
	      localLog.error(paramString);
	    
	  }
}
