package com.fastcampus.bookmanager.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
	@Test
	void test() {
		User user = new User();
		user.setEmail("asdf@aaa.com");
		user.setName("asdf");
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

		// User user1 = new User(null,"asdf", "asdf@aaa.com", LocalDateTime.now(), LocalDateTime.now());
		User user2 = new User("asdf", "asdf@aaa.com");

		User user3 = User.builder()
				.name("asdf")
				.email("asdf@asdf.com")
				.build();

		System.out.println(user);
		// System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
	}
}
