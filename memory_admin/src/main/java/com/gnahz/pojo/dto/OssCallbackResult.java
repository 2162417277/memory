package com.gnahz.pojo.dto;


/**
 * TODO oss上传文件的回调结果
 *
* @Author 张伟洁
* Date:2024-01-12-11:23
* @create 忆项目(小白)
 */
public class OssCallbackResult {
    private String filename;// 文件名称
    private String size;// 文件大小
    private String mimeType;// 文件的mimeType
    private String width;// 图片文件的宽
    private String height;// 图片文件的高

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}





