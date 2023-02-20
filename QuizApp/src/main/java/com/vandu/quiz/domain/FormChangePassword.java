package com.vandu.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormChangePassword {
	private String password;
	private String newPassword;
	private String rePassword;
}
