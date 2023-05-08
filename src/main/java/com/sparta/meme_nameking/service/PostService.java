package com.sparta.meme_nameking.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.meme_nameking.dto.PostResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.repository.PostRepository;
import com.sparta.meme_nameking.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final AmazonS3Client amazonS3Client;
    private final PostRepository postRepository;
    private final Utils utils;
    private static final String S3_BUCKET_PREFIX = "S3";

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    Post post;

    // 게시글 작성
    @Transactional
    public ResponseMsgDto<?> createPost(MultipartFile image, User user) throws IOException {
        // 파일명 새로 부여를 위한 현재 시간 알아내기
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);

        String imageUrl = null;

        // 새로 부여한 이미지명
        String newFileName = "image" + hour + minute + second + millis;
        String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1");
        String imageName =S3_BUCKET_PREFIX + newFileName + fileExtension;

        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getSize());

        InputStream inputStream = image.getInputStream();

        amazonS3Client.putObject(new PutObjectRequest(bucketName, imageName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        imageUrl = amazonS3Client.getUrl(bucketName, imageName).toString();

        post = post.builder()
                .imageUrl(imageUrl)
                .user(user)
                .build();

        postRepository.save(post);

        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 작성 완료", new PostResponseDto(post));
    }

    // 게시글 수정
    @Transactional
    public ResponseMsgDto<?> updatePost(Long id, MultipartFile image, User user) throws IOException{
        post = utils.findPostById(id);
        utils.findAuthorById(id, user.getId());

        // 폴더 생성과 파일명 새로 부여를 위한 현재 시간 알아내기
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);

        String imageUrl;

        // 새로 부여한 이미지명
        String newFileName = "image" + hour + minute + second + millis;
        String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1");
        String imageName =S3_BUCKET_PREFIX + newFileName + fileExtension;

        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getSize());

        InputStream inputStream = image.getInputStream();

        amazonS3Client.putObject(new PutObjectRequest(bucketName, imageName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        imageUrl = amazonS3Client.getUrl(bucketName, imageName).toString();

        post.update(imageUrl);

        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 수정 완료", new PostResponseDto(post));
    }

    // 게시글 삭제
    @Transactional
    public ResponseMsgDto<?> deletePost(Long id, User user) {
        utils.findPostById(id);
        utils.findAuthorById(id, user.getId());
        postRepository.deleteById(id);
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 삭제 완료", id);
    }

}
