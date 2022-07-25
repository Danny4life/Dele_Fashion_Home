package com.example.dele_fashion_home.model;

import com.example.dele_fashion_home.dto.LikeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "liked_items")
@IdClass(LikeDto.class)
public class LikeEntity implements Serializable {

    @Id
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private PostEntity posts;

    @Id
    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private  UserEntity user;

    @CreationTimestamp
    private LocalDateTime timeLiked;
}
