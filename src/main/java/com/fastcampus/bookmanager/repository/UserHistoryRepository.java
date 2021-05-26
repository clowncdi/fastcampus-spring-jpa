package com.fastcampus.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.bookmanager.domain.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
