package com.fastcampus.bookmanager.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fastcampus.bookmanager.domain.listener.Auditable;
import com.fastcampus.bookmanager.domain.listener.UserEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
// @Table(name = "user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity implements Auditable {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String name;

	@NonNull
	// @Column(unique = true)
	private String email;

	// @Column(updatable = false)
	// @CreatedDate
	// private LocalDateTime createdAt;
	//
	// // @Column(insertable = false)
	// @LastModifiedDate
	// private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Transient
	private String testData;

	// @OneToMany(fetch = FetchType.EAGER)
	// private List<Address> address;

	// public User(@NonNull String name, @NonNull String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
	// 	this.name = name;
	// 	this.email = email;
	// 	this.createdAt = createdAt;
	// 	this.updatedAt = updatedAt;
	// }

	// @PrePersist
	// @PreUpdate
	// @PreRemove
	// @PostPersist
	// @PostUpdate
	// @PostRemove
	// @PostLoad

	// @PrePersist
	// public void prePersist() {
	// 	this.createdAt = LocalDateTime.now();
	// 	this.updatedAt = LocalDateTime.now();
	// }
	//
	// @PreUpdate
	// public void preUpdate() {
	// 	this.updatedAt = LocalDateTime.now();
	// }

	// @PrePersist
	// public void prePersist() {
	// 	System.out.println(">>> prePersist");
	// }
	//
	// @PostPersist
	// public void postPersist() {
	// 	System.out.println(">>> postPersist");
	// }
	//
	// @PreUpdate
	// public void preUpdate() {
	// 	System.out.println(">>> preUpdate");
	// }
	//
	// @PostUpdate
	// public void postUpdate() {
	// 	System.out.println(">>> postUpdate");
	// }
	//
	// @PreRemove
	// public void preRemove() {
	// 	System.out.println(">>> preRemove");
	// }
	//
	// @PostRemove
	// public void postRemove() {
	// 	System.out.println(">>> postRemove");
	// }
	//
	// @PostLoad
	// public void postLoad() {
	// 	System.out.println(">>> postLoad");
	// }
}
