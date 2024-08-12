package com.tenco.bank.repository.model;

import java.security.Timestamp;

import org.springframework.http.HttpStatus;

import com.tenco.bank.handler.exception.DataDeliveryException;
import com.tenco.bank.utils.Define;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {

	private Integer id;
	private String number;
	private String password;
	private Long balance; // 계좌잔액
	private Integer userId;
	private Timestamp createdAt;
	
	// 출금 기능
	public void withdraw(Long amount) {
		// 방어적 코드
		this.balance -= amount;
	}
	
	// 입금 기능
	public void deposit(Long amount) {
		this.balance += amount;
	}
	
	// 패스워드 체크
	public void checkPassword(String password) {
		
		//                  f             ==   f  -> true
		if(this.password.equals(password) == false) {
			throw new DataDeliveryException(Define.FAIL_ACCOUNT_PASSWROD, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 잔액 여부 확인
	public void checkBalance(Long amount) {
		// 출금
		// 만약 내가 가진 금액이 출금하려는 금액보다 많으면 안되게
		if(this.balance < amount) {
			throw new DataDeliveryException(Define.LACK_Of_BALANCE, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 계좌 소유자 확인 기능
	public void checkOwner(Integer principalId) {
		
		// 만약 내 계좌가 아니면 안되게
		if(this.userId != principalId) {
			throw new DataDeliveryException(Define.NOT_ACCOUNT_OWNER, HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
