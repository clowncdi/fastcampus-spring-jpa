package com.fastcampus.bookmanager.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fastcampus.bookmanager.domain.listener.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
@Data
// @EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity implements Auditable {
	@Id
	@GeneratedValue
	private Long id;

	private Long userId;

	private String name;

	private String email;

	// @CreatedDate
	// private LocalDateTime createdAt;
	//
	// @LastModifiedDate
	// private LocalDateTime updatedAt;
}
