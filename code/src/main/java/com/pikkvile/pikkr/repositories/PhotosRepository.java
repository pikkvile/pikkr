package com.pikkvile.pikkr.repositories;

import com.pikkvile.pikkr.model.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photo, Integer> {
}
