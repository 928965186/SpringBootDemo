package com.test.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * 文件操作日志类
 * @author john
 *
 */
public class FileUtil {
	private static LogUtil log =new LogUtil();
	/**
	 * 创建文件夹 因为文件夹以uuid命名 所以不判断唯一性
	 * @param dirName 文件路径
	 * @return 
	 */
	public static String CreateDir(String dirName) {
		File file=new File(dirName);
		if (file.mkdirs()) {
		
			log.writeLog("创建文件夹成功，文件名为："+file+"。");
			return file.toString();
		}else {
			log.writeLog("创建文件夹失败，文件夹："+file+"已存在。");
			return file.toString();
		}
	}
	/**
     * 输出某个文件夹下的所有文件和文件路径
     * key为文件名
     * value为文件路径
     * @param delpath
     *            String
     * @throws FileNotFoundException
     * @throws IOException
     * @return boolean
     */
    public static Map<String,String> readfileAll(String filepath)
            throws FileNotFoundException, IOException {
    	 Map<String,String> map=new HashMap<String, String>();
    	if("".equals((filepath))){
    		return map;
    	}
    	
        File file = new File(filepath);
		// 当且仅当此抽象路径名表示的文件存在且 是一个目录时（即文件夹下有子文件时），返回 true
		if (!file.isDirectory()) {
		} else if (file.isDirectory()) {
		    // 得到目录中的文件和目录
		    String[] filelist = file.list();
		    if (filelist.length == 0) {
		    } else {
		    }
		    for (int i = 0; i < filelist.length; i++) {
		        File readfile = new File(filepath + File.separator + filelist[i]);
		        map.put(filelist[i], readfile.getAbsolutePath());
		    }

		}
        return map;
    }
    
    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//  // 删除单个文件
//  String file = "c:/test/test.txt";
//  DeleteFileUtil.deleteFile(file);
//  System.out.println();
        // 删除一个目录
        String dir = "D:/home/web/upload/upload/files";
        FileUtil.deleteDirectory(dir);
//  System.out.println();
//  // 删除文件
//  dir = "c:/test/test0";
//  DeleteFileUtil.delete(dir);

    }
    
}
