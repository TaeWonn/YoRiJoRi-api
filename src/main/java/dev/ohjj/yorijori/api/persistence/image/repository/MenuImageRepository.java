package dev.ohjj.yorijori.api.persistence.image.repository;

import dev.ohjj.yorijori.api.persistence.image.entity.MenuImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuImageRepository extends JpaRepository<MenuImageEntity, Long> {
}
