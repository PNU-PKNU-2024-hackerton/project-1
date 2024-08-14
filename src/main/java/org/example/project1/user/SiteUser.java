package org.example.project1.user;

import jakarta.persistence.*;
import lombok.*;
import org.example.project1.board.Board;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siteuser_id")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "token")
    private String token;

    @OneToMany(mappedBy = "author")
    private List<Board> boards;
}