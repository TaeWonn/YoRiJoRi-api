package dev.ohjj.yorijori.api.image.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String path;

    @Column(length = 300, nullable = false)
    private String fileName;

    @Column(length = 10, nullable = false)
    private String fileExtension;

    @Column(nullable = false)
    private Byte imageGroup;

    @Column()
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column()
    @ColumnDefault("false")
    private Boolean deleteAt;



    @PrePersist
    public void prePersist() {
        this.regDate = Objects.nonNull(this.regDate) ? this.regDate :LocalDateTime.now();
        this.deleteAt = Objects.nonNull(this.deleteAt) ? this.deleteAt : false;
    }

}
