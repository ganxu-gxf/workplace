package com.example.gameSystem.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/tool")
public class UploadImg {

    @Value("${web.upload-path}")
    private String uploadPath;



    private String[] data;

    private int errno;

    private String msg;

    /**
     * 上传图片到项目文件夹
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadImg")
    public Map uploadPicture(MultipartFile file, HttpServletRequest request) {
        File targetFile = null;
        String url = "";//返回存储路径
        ErrorInfo error = new ErrorInfo();
        Map map = new HashMap();
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
//            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";//存储路径

            String path = uploadPath; //文件存储位置
            File file2 = new File(path);
            if (!file2.exists() && !file2.isDirectory()) {
                file2.mkdir();
            }
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名

            //先判断文件是否存在
            String fileAdd = new SimpleDateFormat("yyyyMMdd").format(new Date());

            File file1 = new File(path + "/" + fileAdd);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            targetFile = new File(file1, fileName);
            try {
                file.transferTo(targetFile);
                url = "/" + fileAdd + "/" + fileName;
                error.msg = "上传成功";
                error.code = 1;
            } catch (Exception e) {
                e.printStackTrace();
                error.msg = "上传图片异常，请检查后再试";
                error.code = 0;
            }
        } else {
            error.msg = "上传失败";
            error.code = 0;
        }
        map.put("error", error);
        map.put("url", url);
        map.put("size",file.getSize());
        return map;
    }
    /**
     * 上传图片到项目文件夹
     *
     * @param file
     * @return
     */
    @RequestMapping("/multiFileUpload")
    public Map multiFileUpload(MultipartFile[] file) {
        ErrorInfo error = new ErrorInfo();
        Map map = new HashMap();
        File targetFile = null;
        String url = "";//返回存储路径
        String urlStr = "";//返回存储路径
        String[] str = new String[file.length];
        if (file.length > 3) {
            error.msg = "上传图片最多只能三张";
            error.code = 0;
            map.put("error", error);
            return map;
        }
        for (int i=0;i<file.length;i++){
            String fileName = file[i].getOriginalFilename();//获取文件名加后缀
            if (fileName != null && fileName != "") {
//            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";//存储路径
                String path = uploadPath; //文件存储位置
                File file2 = new File(path);
                if (!file2.exists() && !file2.isDirectory()) {
                    file2.mkdir();
                }
                String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
                System.out.println("fileF:" + fileF);
                fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名

                //先判断文件是否存在
                String fileAdd = new SimpleDateFormat("yyyyMMdd").format(new Date());

                File file1 = new File(path + "/" + fileAdd);
                //如果文件夹不存在则创建
                if (!file1.exists() && !file1.isDirectory()) {
                    file1.mkdir();
                }
                targetFile = new File(file1, fileName);
                try {
                    file[i].transferTo(targetFile);
                    url = "/" + fileAdd + "/" + fileName;
                    error.msg = "上传成功";
                    error.code = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    error.msg = "上传图片异常，请检查后再试";
                    error.code = 0;
                }
            } else {
                error.msg = "上传失败";
                error.code = 0;
            }
            str[i]=url;
            urlStr+=url+",";
        }
        map.put("error", error);
        map.put("urlStrArray", str);
        map.put("urlStr",urlStr);
        return map;
    }

//    /**
//     * 文本编辑器的图片保存
//     * @param file
//     * @return
//     */
//    @RequestMapping("/uploadEditor")
//    public String uploadEditor(@RequestParam(value = "uploadEditor") MultipartFile file) {
//        File targetFile = null;
//        String url = "";//返回存储路径
//        UploadImg uploadImg=new UploadImg();
//        Map map = new HashMap();
//
//        String fileName = file.getOriginalFilename();//获取文件名加后缀
//        if (fileName != null && fileName != "") {
////            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";//存储路径
//
//            String path = uploadPath; //文件存储位置
//            File file2 = new File(path);
//            System.out.println("path:" + path);
//            if (!file2.exists() && !file2.isDirectory()) {
//                file2.mkdir();
//            }
//            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
//            System.out.println("fileF:" + fileF);
//            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名
//
//            //先判断文件是否存在
//            String fileAdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
//
//            File file1 = new File(path + "/" + fileAdd);
//            //如果文件夹不存在则创建
//            if (!file1.exists() && !file1.isDirectory()) {
//                file1.mkdir();
//            }
//            targetFile = new File(file1, fileName);
//            try {
//                file.transferTo(targetFile);
//                url = "/" + fileAdd + "/" + fileName;
//                System.out.println("url:" + url);
//                uploadImg.msg="上传成功";
//                uploadImg.errno=0;
//                String [] data1=new String[1];
//                data1[0]=url;
//                uploadImg.data=data1;
//            } catch (Exception e) {
//                e.printStackTrace();
//                uploadImg.msg="上传图片异常，请检查后再试";
//                uploadImg.errno=-99;
//            }
//        } else {
//            uploadImg.msg="上传失败";
//            uploadImg.errno=-99;
//        }
//        return JSON.toJSONString(uploadImg);
//    }


    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Base64图片上传
     */
    @RequestMapping(value = "/generateImage",method = RequestMethod.POST)
    public Map generateImage(String filename) {
        ErrorInfo error = new ErrorInfo();
        Map map = new HashMap();
        String url = "";//返回存储路径
        if (filename == null || filename == ""){
            error.code=0;
            error.msg="上传图片失败";
            map.put("error",error);
            return map;
        }
        //后缀名
        String SuffixName = filename.substring(filename.indexOf("/") + 1, filename.indexOf(";"));
        if (SuffixName != null && SuffixName != "") {
            String path = uploadPath; //文件存储位置
            File file2 = new File(path);
            if (!file2.exists() && !file2.isDirectory()) {
                file2.mkdir();
            }
            String fileF ="."+SuffixName;
            SuffixName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名
            //先判断文件是否存在
            String fileAdd = new SimpleDateFormat("yyyyMMdd").format(new Date());

            File file1 = new File(path + "/" + fileAdd);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            url = path+"/" + fileAdd + "/" + SuffixName;
            filename=filename.substring(filename.indexOf(',')+1,filename.length());
            //filename=filename.replace("data:image/png;base64,","");
            BASE64Decoder decoder = new BASE64Decoder();
            try{
                byte[] b = decoder.decodeBuffer(filename);
                for (int i=0;i<b.length;i++){
                    if(b[i]<0){
                        b[i]+=256;
                    }
                }
                OutputStream out = new FileOutputStream(url);
                url="/" + fileAdd + "/" + SuffixName;
                out.write(b);
                out.flush();
                out.close();
                map.put("size",b.length);
                error.code=1;
                error.msg="上传图片成功";
            }catch (Exception e){
                e.printStackTrace();
                error.code=0;
                error.msg="上传图片失败";
            }
        }
        map.put("error",error);
        map.put("urlStr",url);
        return map;
    }
}
