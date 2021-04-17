package dev.ohjj.yorijori.api.persistence.image.entity;

import lombok.Getter;
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
@Table(name = "restaurant_image")
public class RestaurantImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public RestaurantImageEntity (String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public static RestaurantImageEntity from(String path, String fileName) {
        final var restaurantImageEntity = new RestaurantImageEntity(path, fileName);
        final var now = LocalDateTime.now();
        restaurantImageEntity.regDate = now;
        restaurantImageEntity.modDate = now;
        restaurantImageEntity.deleteAt = false;
        return restaurantImageEntity;
    }

}
