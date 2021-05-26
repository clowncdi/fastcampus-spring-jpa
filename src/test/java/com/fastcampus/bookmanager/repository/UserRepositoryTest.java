package com.fastcampus.bookmanager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fastcampus.bookmanager.domain.Gender;
import com.fastcampus.bookmanager.domain.User;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserHistoryRepository userHistoryRepository;

	// @BeforeEach
	// void init() {
	// 	createSample();
	// }

	private void createSample() {
		User user1 = new User("martin", "martin@fastcampus.com");
		User user2 = new User("dennis", "dennis@fastcampus.com");
		User user3 = new User("sophia", "sophia@slowcampus.com");
		User user4 = new User("james", "james@slowcampus.com");
		User user5 = new User("martin", "martin@slowcampus.com");
		userRepository.saveAllAndFlush(Lists.newArrayList(user1, user2, user3, user4, user5));
		System.out.println("--------------------------------------------------------");
	}

	@Test
	void crud1() {
		List<User> users1 = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
		List<User> users2 = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
		users1.forEach(System.out::println);

		User user1 = new User("jack", "jack@fast.com");
		User user2 = new User("steve", "steve@fast.com");
		userRepository.saveAll(Lists.newArrayList(user1, user2));

		List<User> users = userRepository.findAll();
		users.forEach(System.out::println);
	}

	@Test
	void crud2() {
		User user1 = new User("jack", "jack@fast.com");
		userRepository.save(user1);
		User user = userRepository.findById(1L).orElse(null);
		System.out.println(user);
	}

	@Test
	void crud3() {
		userRepository.saveAndFlush(new User("jack", "jack@fast.com"));
		// userRepository.flush();
		userRepository.findAll().forEach(System.out::println);
	}

	@Test
	void crud4() {
		long count = userRepository.count();
		System.out.println("count = " + count);
		userRepository.findAll().forEach(System.out::println);

		boolean exists = userRepository.existsById(1L);
		System.out.println("exists = " + exists);

		// userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
		// userRepository.deleteById(1L);
		// userRepository.deleteAll();
		// userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));		// 성능이슈! find한 후 delete 실행하기 때문에. deleteInBatch 추천.
		userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // find where in 조건 검색 후 where id=? or id=? 로 삭제
		userRepository.deleteAllInBatch();	// find 검색없이 where도 없는 delete구문 실행

		userRepository.findAll().forEach(System.out::println);
	}

	@Test
	void crud5() {
		Page<User> users = userRepository.findAll(PageRequest.of(0, 3));

		System.out.println("page : " + users);
		System.out.println("totalElements : " + users.getTotalElements());
		System.out.println("totalPages : " + users.getTotalPages());
		System.out.println("numberOfElements : " + users.getNumberOfElements());
		System.out.println("sort : " + users.getSort());
		System.out.println("size : " + users.getSize());
		users.getContent().forEach(System.out::println);
	}

	@Test
	void crud6() {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("name")
				.withMatcher("email", endsWith());

		Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);

		userRepository.findAll(example).forEach(System.out::println);
	}

	@Test
	void crud7() {
		User user = new User();
		user.setEmail("slow");
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("email", contains());
		Example<User> example = Example.of(user, matcher);

		userRepository.findAll(example).forEach(System.out::println);
	}

	@Test
	void update() {
		userRepository.save(new User("david", "david@fastcampus.com"));

		User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		user.setEmail("martin-updated@fastcampus.com");

		userRepository.save(user);	// select해서 있는지 검색한 후 update 쿼리 수행
	}

	@Test
	void select() {
		//Query Method 활용
		System.out.println(userRepository.findByName("dennis"));
		System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
		System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
		System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
		System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
		System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
		System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
		System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));
		System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("martin@fastcampus.com"));
		System.out.println("findTop2ByName : " + userRepository.findTop2ByName("martin"));
		System.out.println("findFirst2ByName : " + userRepository.findFirst2ByName("martin"));
		System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));	//Last1은 이식하지 않은 키워드라서 무시됨
	}

	@Test
	void select2() {
		//Query Method 활용
		System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin"));
		System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "dennis"));
		System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "martin"));
		System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "dennis"));

		System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
		System.out.println("findByIDAfter : " + userRepository.findByIdAfter(4L));
		System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
		System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

		System.out.println("findByCreateAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
		System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));	// 1, 3번도 포함되어 결과출력
		System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));	// 1, 3번도 포함되어 결과출력
	}

	@Test
	void select3() {
		System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
		// System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());	//문자열의 NotEmpty가 아닌 콜렉션 타입의 Not Empty를 체크함

		System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));

		System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
		System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
		System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
		System.out.println("findByNameLike : " + userRepository.findByNameLike("%" + "art" + "%"));
	}

	@Test
	void pagingAndSortingTest() {
		System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
		System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("martin"));	//역순해서 젤 위에 1개 조회
		System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
		System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin", getSort()));
		System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(1, 1, Sort.by(
				Sort.Order.desc("id")))).getTotalElements());
	}

	private Sort getSort() {
		return Sort.by(
			Sort.Order.desc("id"),
			Sort.Order.asc("email"),
			Sort.Order.desc("createdAt"),
			Sort.Order.asc("updatedAt")
		);
	}

	@Test
	void insertAndUpdateTest() {
		User user = new User();
		user.setName("martin");
		user.setEmail("martin2@fastcampus.com");
		userRepository.save(user);

		User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		user2.setName("marrrrrtin");
		userRepository.save(user2);
	}

	@Test
	void enumTest() {
		User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		user.setGender(Gender.MALE);

		userRepository.save(user);

		userRepository.findAll().forEach(System.out::println);

		System.out.println(userRepository.findRawRecord().get("gender"));
	}

	@Test
	void listenerTest() {
		User user = new User();
		user.setName("martin2");
		user.setEmail("martin2@fastcampus.com");
		userRepository.save(user);

		User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		user2.setName("marrrrrrrtin");
		userRepository.save(user2);

		userRepository.deleteById(4L);
	}

	@Test
	void prePersistTest() {
		User user = new User();
		user.setName("martin2");
		user.setEmail("martin2@fastcampus.com");
		// user.setCreatedAt(LocalDateTime.now());
		// user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);

		System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
	}

	@Test
	void preUpdateTest() {
		User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
		System.out.println("as-is : " + user);

		user.setName("martin2");
		userRepository.save(user);

		System.out.println("to-be : " + userRepository.findAll().get(0));
	}

	@Test
	void userHistoryTest() {
		User user = new User();
		user.setEmail("martin-new@fastcampus.com");
		user.setName("martin-new");

		userRepository.save(user);

		user.setName("martin-new-new");

		userRepository.save(user);

		userHistoryRepository.findAll().forEach(System.out::println);
	}
}
