/*
package com.lhc.common;

import com.ys.xlb.exception.GlobalException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

*/
/**
 * @author lihaichao
 * @ClassName uploadUtils
 * @description:  上传工具类
 * @time 2019/1/3 9:17
 **//*

@Component
public class UploadUtils {

    private static UploadUtils uploadUtils;
    @Resource
    private Environment env;

    @PostConstruct
    public void init(){
        uploadUtils = this;
        uploadUtils.env = this.env;
    }

    //文件上传 返回文件路径
    public static String fileUpload(MultipartFile file){
        String uploadPath = uploadUtils.env.getProperty("file.win-uploadPath");
        String systemName = System.getProperty("os.name");
        if(systemName != null && systemName.toLowerCase().indexOf("linux") > -1){
            uploadPath = uploadUtils.env.getProperty("file.linux-uploadPath");
        }
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newName = StringUtils.getRandomStringByLength(5)+"_"+System.currentTimeMillis()+ext;
        //新建 uploadFile 文件夹    不存在就新建
        File parentPath=new File(uploadPath);
        if (!parentPath.exists()) {
            if (!parentPath.mkdirs()) {
                throw new RuntimeException("can't make csv  directory:[" + parentPath + "]");
            }
        }
        if (!parentPath.canWrite() && !parentPath.canRead()) {
            throw new RuntimeException("no read/write right to target csv directory:[" + parentPath + "]!");
        }
        return getUrl(file, newName, parentPath);
    }


    private static String getUrl(MultipartFile file, String newName, File parentPath) {
        String src="";
        try {
            file.transferTo(new File(parentPath,newName));
            File theFile=new File(parentPath+"/"+newName);
            if(theFile.exists()){
                //拼接图片的相对路径作为URL
                src="/files/"+newName;
                System.out.println(src);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return src;
    }



    //下载文件
    public static void downloadFile(String filePath,HttpServletRequest request,HttpServletResponse response) {
        String path=uploadUtils.env.getProperty("file.uploadPath")+getPath(filePath);
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String realName=filename.substring(filename.indexOf("_")+1,filename.length());
            if ("FF".equals(getBrowser(request))) {
                // 针对火狐浏览器处理方式不一样了
                realName = new String(realName.getBytes("GB2312"), "ISO-8859-1");
            }else{
                realName = URLEncoder.encode(realName, "UTF-8");//encode解决中文乱码
                realName = realName.replace("+", "%20");//encode后替换  解决空格问题
            }
            response.addHeader("Content-Disposition", "attachment;filename="+realName+"");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    //删除文件
    public static void deleteFile(String path){
        if(path != null && !path.equals("") && path.indexOf("files") != -1){
            String uploadPath = uploadUtils.env.getProperty("file.win-uploadPath");
            String systemName = System.getProperty("os.name");
            if(systemName != null && systemName.toLowerCase().indexOf("linux") > -1){
                uploadPath = uploadUtils.env.getProperty("file.linux-uploadPath");
            }
            String filePath=uploadPath+getPath(path);
            System.out.println(filePath);
            File file=new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    //获取路径
    public static String getPath(String path){
        String filePath = "";
        if(path.substring(0,6).equals("/files")){
            filePath = path.substring(6,path.length());
        }else{
            throw new GlobalException(Code.FILE_PATH_ERROR);
        }
        return filePath;
    }

    */
/**
     * @Title: getBrowser
     * @Description: 判断客户端浏览器
     * @return String
     * @author lihaichao
     * @date 2017年12月2日上午10:55:24
     *//*

    private static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") != -1)
                return "IE";
            if (UserAgent.indexOf("firefox") != -1)
                return "FF";
            if (UserAgent.indexOf("safari") != -1)
                return "SF";
        }
        return null;
    }

}
*/
