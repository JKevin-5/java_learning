package com.jkevin.gradle_springboot_data_jpa.repository;

import com.jkevin.gradle_springboot_data_jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
