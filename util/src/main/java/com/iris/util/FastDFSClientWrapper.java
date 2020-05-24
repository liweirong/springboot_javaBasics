package com.iris.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.sun.deploy.association.utility.AppConstants;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ProtoCommon;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * @author iris
 * @date 2020/5/24
 */

    @Component
    public class FastDFSClientWrapper {

        @Autowired
        private FastFileStorageClient storageClient;


        /**
         * 上传文件
         * @param file 文件对象
         * @return 文件访问地址
         * @throws IOException
         */
        public String uploadFile(MultipartFile file) throws IOException {
            StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
            return getResAccessUrl(storePath);
        }

        /**
         * 将一段字符串生成一个文件上传
         * @param content 文件内容
         * @param fileExtension
         * @return
         */
        public String uploadFile(String content, String fileExtension) {
            byte[] buff = content.getBytes(Charset.forName("UTF-8"));
            ByteArrayInputStream stream = new ByteArrayInputStream(buff);
            StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
            return getResAccessUrl(storePath);
        }

    private String getResAccessUrl(StorePath storePath) {
        String fileUrl =
//                AppConstants.HTTP_PRODOCOL
//                + appConfig.getResHost()+ ":"
//                + appConfig.getFdfsStoragePort() + "/"
//                +
                        storePath.getFullPath();
        return fileUrl;
    }
    // 封装图片完整URL地址

        /**
         * 删除文件
         * @param fileUrl 文件访问地址
         * @return
         */
        public void deleteFile(String fileUrl) {
            if (StringUtils.isEmpty(fileUrl)) {
                return;
            }
            try {
                StorePath storePath = StorePath.parseFromUrl(fileUrl);
                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            } catch (FdfsUnsupportStorePathException e) {
                e.printStackTrace();
            }
        }




    @Value("${fdfs.web-server-url}")
    private String fastDfsUrl;

    @Value("${fdfs.http.secret_key}")
    private String fastDfsKey;

    /**
     * 获取带有token的访问地址
     *
     * @param fileUrl 示例：group1/M00/00/00/L2ZUml6QisqAUJE3AIOPO1HT6Bo274.mp4
     * @return java.lang.String 示例：http://yourIp:port/files/group1/M00/00/00/L2ZUml6QisqAUJE3AIOPO1HT6Bo274.mp4?token=e9a6ae7f1ecca6fed51e248c6a10d3bc&ts=1589361883
     */
    public String getTokenUrl(String fileUrl) throws Exception {
        String path = StorePath.parseFromUrl(fileUrl).getPath();
        //时间戳 单位为秒
        int ts = (int) (System.currentTimeMillis() / 1000);
        String token;
        try {
            token = ProtoCommon.getToken(path, ts, fastDfsKey);
        } catch (Exception e) {
            throw new Exception("FastDFS获取token异常");
        }

        return fastDfsUrl + fileUrl + "?token=" + token + "&ts=" + ts;
    }


}
