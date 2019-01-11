package com.test.test;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class XMLTest {

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"urn:enterprise.soap.sforce.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><loginResponse><result><metadataServerUrl>https://8848phone--uat.my.salesforce.com/services/Soap/m/44.0/00D5D0000008uok</metadataServerUrl><passwordExpired>false</passwordExpired><sandbox>true</sandbox><serverUrl>https://8848phone--uat.my.salesforce.com/services/Soap/c/44.0/00D5D0000008uok/0DF5D0000008Ol7</serverUrl><sessionId>00D5D0000008uok!ARkAQAOg47SZSxeGi2Ch80bQjZEMRsMcEs.Lwic7SYz1CvGq2V1XMiStyb6aEeEy3zYMY1yu6wFirtvlhE4b53_dgrhB.3tR</sessionId><userId>0056F00000AAS5kQAH</userId><userInfo><accessibilityMode>false</accessibilityMode><chatterExternal>false</chatterExternal><currencySymbol>￥</currencySymbol><orgAttachmentFileSizeLimit>5242880</orgAttachmentFileSizeLimit><orgDefaultCurrencyIsoCode>CNY</orgDefaultCurrencyIsoCode><orgDefaultCurrencyLocale>zh_CN</orgDefaultCurrencyLocale><orgDisallowHtmlAttachments>false</orgDisallowHtmlAttachments><orgHasPersonAccounts>true</orgHasPersonAccounts><organizationId>00D5D0000008uokUAA</organizationId><organizationMultiCurrency>false</organizationMultiCurrency><organizationName>清华同方股份有限公司</organizationName><profileId>00e6F0000039OlmQAE</profileId><roleId xsi:nil=\"true\"/><sessionSecondsValid>7200</sessionSecondsValid><userDefaultCurrencyIsoCode xsi:nil=\"true\"/><userEmail>bob.wang@celnet.com.cn</userEmail><userFullName>王延浩</userFullName><userId>0056F00000AAS5kQAH</userId><userLanguage>zh_CN</userLanguage><userLocale>zh_CN</userLocale><userName>bob.wang@8848.com.uat</userName><userTimeZone>Asia/Shanghai</userTimeZone><userType>Standard</userType><userUiSkin>Theme3</userUiSkin></userInfo></result></loginResponse></soapenv:Body></soapenv:Envelope>\r\n"
				+ "";
		
		 JSONObject xmlToJson = JSONUtil.xmlToJson(xml);
		 System.out.println(xmlToJson);
	}
	
	

}
