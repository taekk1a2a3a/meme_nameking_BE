package com.sparta.meme_nameking.service;

import com.sparta.meme_nameking.dto.PostResponseDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Value("${file.upload.location}")
    private String upLoadLocation;

    Post post;

    // 게시글 작성
    @Transactional
    public ResponseMsgDto<?> createPost(MultipartFile image, User user) throws IOException {
        // 폴더 생성과 파일명 새로 부여를 위한 현재 시간 알아내기
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);

        // 파일이 저장될 절대 경로   

        String absolutePath = new File(upLoadLocation).getAbsolutePath() + "/";

        // 새로 부여한 이미지명
        String newFileName = "image" + hour + minute + second + millis;
        // 정규식 이용하여 확장자만 추출
        String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1");
        // 저장될 폴더 경로
        String path = "images/test/" + year + "_" + month + "_" + day;

        if(!image.isEmpty()) {
            File file = new File(absolutePath + path);
            if(!file.exists()){
                // mkdir()과 다르게 상위 폴더가 없을 때 상위폴더까지 생성
                file.mkdirs();
            }

            file = new File(absolutePath + path + "/" + newFileName + fileExtension);
            image.transferTo(file);

            String originImageName = image.getOriginalFilename();
            String imagePath = path;
            String imageName = newFileName + fileExtension;

            post = postRepository.save(new Post(originImageName, imagePath, imageName, user));
        }
        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 작성 완료", new PostResponseDto(post));
    }

//    // 게시글 수정
//    @Transactional
//    public ResponseMsgDto<?> updatePost(Long id, PostRequestDto postRequestDto, User user) {
//        post = postRepository.findById(id).orElseThrow(
//                () -> new CustomException(POST_NOT_FOUND)
//        );
//        post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
//                () -> new CustomException(AUTHOR_NOT_SAME_MOD)
//        );
//        post.update(postRequestDto);
//        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 수정 완료", new PostResponseDto(post));
//    }
//
//    // 게시글 삭제
//    @Transactional
//    public ResponseMsgDto<?> deletePost(Long id, User user) {
//        post = postRepository.findById(id).orElseThrow(
//                () -> new CustomException(POST_NOT_FOUND)
//        );
//        post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
//                () -> new CustomException(AUTHOR_NOT_SAME_MOD)
//        );
//        postRepository.deleteById(id);
//        return ResponseMsgDto.setSuccess(HttpStatus.OK.value(), "게시글 삭제 완료", id);
//    }

}
