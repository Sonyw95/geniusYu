package com.Boad;

import org.springframework.web.multipart.MultipartFile;


// 파입 업로드에 관한 Vo 입니다 해당 기능은 현 상황으로 쓰지않고 있습니다 구현중입니다.
public class FileUploadVo {
    private String attachPath; //이미지가 저장될 경로

    private String Filename; //파일이름

    private MultipartFile upload;

    private String CKEditorFuncNum;

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }

    public String getCKEditorFuncNum() {
        return CKEditorFuncNum;
    }

    public void setCKEditorFuncNum(String CKEditorFuncNum) {
        this.CKEditorFuncNum = CKEditorFuncNum;
    }
}
