package dev.ohjj.yorijori.api.persistence.image.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "path", length = 100, nullable = false)
    private String path;

    @Column(name = "file_name", length = 300, nullable = false)
    private String fileName;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "delete_at")
    @ColumnDefault("false")
    private Boolean deleteAt;

    @PrePersist
    public void prePersist() {
        this.regDate = Objects.nonNull(this.regDate) ? this.regDate :LocalDateTime.now();
        this.deleteAt = Objects.nonNull(this.deleteAt) ? this.deleteAt : false;
    }

    public ImageEntity(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public static ImageEntity from(String path, String fileName) {
        return new ImageEntity(path, fileName);
    }

}
