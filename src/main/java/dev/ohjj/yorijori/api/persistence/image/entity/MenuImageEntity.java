package dev.ohjj.yorijori.api.persistence.image.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_image")
public class MenuImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "path", length = 100, nullable = false)
    private String path;

    @Column(name = "file_name", length = 300, nullable = false)
    private String fileName;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "delete_at")
    private Boolean deleteAt;

    public MenuImageEntity (String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public static MenuImageEntity from(String path, String fileName) {
        final var menuImageEntity = new MenuImageEntity(path, fileName);
        final var now = LocalDateTime.now();
        menuImageEntity.regDate = now;
        menuImageEntity.modDate = now;
        menuImageEntity.deleteAt = false;
        return menuImageEntity;
    }
}
