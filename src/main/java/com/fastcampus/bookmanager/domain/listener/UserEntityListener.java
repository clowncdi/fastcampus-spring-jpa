package com.fastcampus.bookmanager.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastcampus.bookmanager.domain.User;
import com.fastcampus.bookmanager.domain.UserHistory;
import com.fastcampus.bookmanager.repository.UserHistoryRepository;
import com.fastcampus.bookmanager.support.BeanUtils;

public class UserEntityListener {
	@PrePersist
	@PreUpdate
	public void prePersistAndpreUpdate(Object o) {
		UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

		User user = (User) o;
		UserHistory userHistory = new UserHistory();
		userHistory.setUserId(user.getId());
		userHistory.setName(user.getName());
		userHistory.setEmail(user.getEmail());

		userHistoryRepository.save(userHistory);
	}
}
