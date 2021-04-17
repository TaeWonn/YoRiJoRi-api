package dev.ohjj.yorijori.api.persistence.image.repository;

import dev.ohjj.yorijori.api.persistence.image.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
