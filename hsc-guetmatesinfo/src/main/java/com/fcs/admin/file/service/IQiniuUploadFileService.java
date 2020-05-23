package com.fcs.admin.file.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;







/**
 * @Author: hsc
 * @Description:七牛上传文件服务
 * @Date: 2020/5/21 21:08
 */
public interface IQiniuUploadFileService {

    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;
}