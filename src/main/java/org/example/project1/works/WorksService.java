package org.example.project1.works;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.project1.board.Board;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class WorksService {
    private final WorksRepository worksRepository;

    /**
     * 작품 등록
     * @param board
     * @param images
     */
    public void uploadWorks(Board board, List<MultipartFile> images) {
        try {
            // 이미지 파일 저장을 위한 경로 설정
            String uploadsDir = "src/main/resources/uploads/works/";

            // 각 이미지 파일에 대해 업로드 및 DB 저장 수행
            for(MultipartFile image: images){

                // 이미지 파일 경로 저장
                String dbFilePath = saveImage(image, uploadsDir);

                // Work 엔티티 생성 및 저장
                Works works = Works.builder()
                        .board(board)
                        .imagePath(dbFilePath)
                        .build();
                worksRepository.save(works);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String saveImage(MultipartFile image, String uploadsDir) throws IOException {
        // 파일 이름 생성
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + image.getOriginalFilename();
        // 실제 파일 저장될 경로
        String filePath = uploadsDir + fileName;
        //DB에 저장될 경로 문자열
        String dbFilePath = "/uploads/works/" + fileName;

        Path path = Paths.get(filePath); // Path 객체 생성
        Files.createDirectories(path.getParent()); // 디렉토리 생성
        Files.write(path, image.getBytes()); // 디렉토리에 파일 저장
        return dbFilePath;
    }
}
