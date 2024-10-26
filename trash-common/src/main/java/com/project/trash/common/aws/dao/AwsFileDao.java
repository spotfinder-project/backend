package com.project.trash.common.aws.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.utils.LogUtils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.SystemResultCode.IMAGE_UPLOAD_FAIL;

@RequiredArgsConstructor
@Repository
public class AwsFileDao {

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  private final AmazonS3 amazonS3;

  /**
   * 파일 업로드
   */
  public String upload(String memberId, String feature, MultipartFile file) {
    final String fileName = createFileName(memberId, FilenameUtils.getExtension(file.getOriginalFilename()));
    final String filePath = createFilePath(feature);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    metadata.setContentType(file.getContentType());

    try {
      // 원본 파일 업로드
      amazonS3.putObject(new PutObjectRequest(bucket + filePath, fileName, file.getInputStream(), metadata));

      // 결과 반환
      return filePath + "/" + fileName;
    } catch (Exception e) {
      LogUtils.info("S3 파일 업로드 중 에러 발생 - " + e.getMessage());
      throw new ValidationException(IMAGE_UPLOAD_FAIL);
    }
  }

  /**
   * 파일명 생성
   */
  private String createFileName(String memberId, String extension) {
    return memberId + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS")) + "." +
        StringUtils.lowerCase(extension);
  }

  /**
   * 파일 경로 생성
   */
  private String createFilePath(String feature) {
    final LocalDate now = LocalDate.now();
    return "/" + feature + "/" + now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
  }
}