package com.github.syann97.restful.myrestfulservice.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
@Table(name = "user")
public class User {
	@Schema(title = "사용자 ID", description = "사용자 ID는 자동 생성")
	@Id
	@GeneratedValue
	private Integer id;

	@Schema(title = "사용자 이름", description = "사용자 이름 입력")
	@Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
	private String name;

	@Schema(title = "사용자 등록일", description = "사용자 등록일 입력")
	@Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다.")
	private Date joinDate;

	@Schema(title = "사용자의 비밀번호", description = "사용자의 비밀번호 입력")
	private String password;

	@Schema(title = "사용자의 주민번호", description = "사용자의 주민번호 입력")
	private String ssn;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User(Integer id, String name, Date joinDate, String password, String ssn) {
		this.id = id;
		this.name = name;
		this.joinDate = joinDate;
		this.password = password;
		this.ssn = ssn;
	}
}
