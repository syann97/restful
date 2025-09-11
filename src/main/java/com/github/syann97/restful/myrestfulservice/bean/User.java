package com.github.syann97.restful.myrestfulservice.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
public class User {
	private Integer id;

	@Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
	private String name;

	@Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다.")
	private Date joinDate;

	private String password;

	private String ssn;
}
