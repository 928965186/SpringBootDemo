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
        String download = download("http://222.84.136.247:808/weaver/weaver.file.FileDownload?fileid=478&download=1","C:\\\\Users\\\\john\\\\Desktop\\\\新建文件夹\\\\");
        System.out.println(download);
    }

    public static String download(String urlPath,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlPath);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        int randomNo=(int)(Math.random()*1000000);
        String filename="5.txt";//获取服务器上图片的名称
        //filename=new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+randomNo;//时间+随机数防止重复
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        String virtualPath="/upload/SDSPage/"+filename;//存入数据库的虚拟路径
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
        return virtualPath;
    }

}