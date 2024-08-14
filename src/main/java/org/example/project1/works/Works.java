package org.example.project1.works;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.project1.board.Board;

// 작품 사진임
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Works {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workds_id")
    private Long id;

    private String description;

    private int workYear;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
