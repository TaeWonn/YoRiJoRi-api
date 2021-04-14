package dev.ohjj.yorijori.api.persistence.image.repository;

import dev.ohjj.yorijori.api.persistence.image.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
