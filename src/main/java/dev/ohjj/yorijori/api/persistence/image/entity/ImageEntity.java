package dev.ohjj.yorijori.api.persistence.image.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate;

    @Column(name = "delete_at", nullable = false)
    private Boolean deleteAt;

    public ImageEntity(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public static ImageEntity from(String path, String fileName) {
        final var imageEntity = new ImageEntity(path, fileName);
        final var now = LocalDateTime.now();
        imageEntity.regDate = now;
        imageEntity.modDate = now;
        imageEntity.deleteAt = false;
        return imageEntity;
    }

}
