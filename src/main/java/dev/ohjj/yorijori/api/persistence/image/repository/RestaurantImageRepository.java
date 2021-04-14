package dev.ohjj.yorijori.api.persistence.image.repository;

import dev.ohjj.yorijori.api.persistence.image.entity.RestaurantImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImageEntity, Long> {

}
