package com.hello.fileUpload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; // 사용자가 업로드한 파일명
    private String storeFileName; // 서버에 올라갈 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
