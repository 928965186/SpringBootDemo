package com.westvalley.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class DownloadImage {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String download = download("http://222.84.136.247:808/weaver/weaver.file.FileDownload?fileid=478&download=1","C:\\\\Users\\\\john\\\\Desktop\\\\�½��ļ���\\\\");
        System.out.println(download);
    }

    public static String download(String urlPath,String savePath) throws Exception {
        // ����URL
        URL url = new URL(urlPath);
        // ������
        URLConnection con = url.openConnection();
        //��������ʱΪ5s
        con.setConnectTimeout(5*1000);
        // ������
        InputStream is = con.getInputStream();
        // 1K�����ݻ���
        byte[] bs = new byte[1024];
        // ��ȡ�������ݳ���
        int len;
        // ������ļ���
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        int randomNo=(int)(Math.random()*1000000);
        String filename="5.txt";//��ȡ��������ͼƬ������
        //filename=new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+randomNo;//ʱ��+�������ֹ�ظ�
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        String virtualPath="/upload/SDSPage/"+filename;//�������ݿ������·��
        // ��ʼ��ȡ
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // ��ϣ��ر���������
        os.close();
        is.close();
        return virtualPath;
    }

}