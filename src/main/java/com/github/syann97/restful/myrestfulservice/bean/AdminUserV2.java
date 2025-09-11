package com.github.syann97.restful.myrestfulservice.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class AdminUserV2 extends AdminUser {
	private String grade;
}
