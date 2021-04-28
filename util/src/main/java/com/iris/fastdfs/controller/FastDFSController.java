package com.iris.fastdfs.controller;

import com.iris.fastdfs.util.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author iris
 * @date 2020/5/21
 */
@RestController
public class FastDFSController {

    @Autowired
    private FastDFSClientWrapper fastDFSClient;

    /**
     * pg,pdf 网页打开
     * group1/M00/00/00/wKirDF7ZFgaAUXlpABHAgxjeB6U877.pdf 下载
     * group1/M00/00/00/wKirDF7ZFlKASc4FAABcVFjALZ075.docx 下载
     * group1/M00/00/00/wKirDF7ZFpaAe1QWAAAXI1XDYhw606.sql 网页打开
     * group1/M00/00/00/wKirDF7ZGlKAQP75AAABx5Vofgw009.txt 网页打开
     * group1/M00/00/00/wKirDF7ZG6OATTExABPZXXkdqvI516.zip 下载
     * group1/M00/00/00/wKirDF7ZHP2AK8ZjAKZks_CMMu4741.mp3
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/images")
    public String saveFile(MultipartFile multipartFile) throws Exception {
        String s = fastDFSClient.uploadFile(multipartFile);
        System.out.println(">>>"+s);
        String tokenUrl = fastDFSClient.getTokenUrl(s);
        System.out.println(">>>>> "+tokenUrl);
        return tokenUrl;
    }
    @GetMapping("/upload/images/")
    public String getToken(@RequestBody String fileUrl){
        String tokenUrl = null;
        try {
            tokenUrl = fastDFSClient.getTokenUrl(fileUrl);
        } catch (Exception e) {
           return "no such images";
        }
        System.out.println(">>>>> "+tokenUrl);
        return tokenUrl;
    }

    @DeleteMapping("/upload/images/")
    public String deleteImages(@RequestBody String fileUrl){
        Boolean aBoolean = fastDFSClient.deleteFile(fileUrl);
        return ""+ aBoolean;
    }

    @DeleteMapping("/images/download")
    public String downImages(@RequestBody String fileUrl, HttpServletResponse response){
        byte[] files = fastDFSClient.download(fileUrl);
        System.out.println(files.length);
        return ""+ files.length;
    }


}
