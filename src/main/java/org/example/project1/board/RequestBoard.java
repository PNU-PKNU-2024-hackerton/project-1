package org.example.project1.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RequestBoard {

    private String name; // 전시 이름
    
    @Size(max = 200)
    private String artistName; // 작가 이름 Board의 author의 username

    private int year;

//    @NotEmpty(message = "이미지는 필수 항목입니다.")
//    private MultipartFile filePath;
}