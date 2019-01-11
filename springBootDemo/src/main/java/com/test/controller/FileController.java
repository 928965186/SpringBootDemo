package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.entity.JsonData;

@Controller
public class FileController {

       //文件放在项目的images下
        private static final String filePath =  "C:\\Users\\john\\Desktop\\java\\springBootDemo\\src\\main\\resources\\static\\images\\";
    
        
        /**
         * 表单上传文件到本地目录
         * @param file
         * @return
         */
         @RequestMapping(value = "upload")
        @ResponseBody
        public JsonData upload(@RequestParam("head_img") MultipartFile file) {
          
             //file.isEmpty(); 判断图片是否为空
             //file.getSize(); 图片大小进行判断
  
        	 // 获取文件名
            String fileName = file.getOriginalFilename();            
            System.out.println("上传的文件名为：" + fileName);
            
            // 获取文件的后缀名,比如图片的jpeg,png
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("上传的后缀名为：" + suffixName);
            
            // 文件上传后的路径
            fileName = UUID.randomUUID() + suffixName;
            System.out.println("转换后的名称:"+fileName);
            
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                //上传成功
                return new JsonData(0, fileName);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
              //上传失败
            return  new JsonData(-1, "fail to save ", null);
        }
         
         /**
          * ajax上传文件到项目目录
          * @param file
          * @return
          */
         @RequestMapping(value="upload2",produces="application/json; charset=utf-8")
         @ResponseBody
         public JsonData upload2(@RequestParam("file") MultipartFile file) {
        	  //判断图片是否为空
        	
             String fileName = file.getOriginalFilename();
             if(fileName.indexOf("\\") != -1){
                 fileName = fileName.substring(fileName.lastIndexOf("\\"));
             }
             String filePath = "src/main/resources/static/images/";
             File targetFile = new File(filePath);
             if(!targetFile.exists()){
                 targetFile.mkdirs();
             }
             FileOutputStream out = null;
             try {
            	  // 获取文件的后缀名,比如图片的jpeg,png
                 String suffixName = fileName.substring(fileName.lastIndexOf("."));
                 System.out.println("上传的后缀名为：" + suffixName);
                 
                 // 文件上传后的路径  用uuid命名防止图片名称重复
                 fileName = UUID.randomUUID() + suffixName;
                 System.out.println("转换后的名称:"+fileName);
              
                 out = new FileOutputStream(filePath+fileName);
                 out.write(file.getBytes());
                 out.flush();
                 out.close();
             } catch (Exception e) {
                 e.printStackTrace();
               
    				return new JsonData(-1, "fail to save ", null);
    			         
             }
            
             return new JsonData(0,"success to upload", fileName);
         }
   
}