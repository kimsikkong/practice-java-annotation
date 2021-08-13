package com.example.practicejavaannotation.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;

    private String description;

    private String thumbnailUrl;

    private Long createdAt;

    public UserProfile(@NonNull User user, String nickname, String thumbnailUrl, Long createdAt) {
        this.user = user;
        this.nickname = nickname;
        this.thumbnailUrl = thumbnailUrl;
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
