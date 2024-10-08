package com.tenco.bank.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public abstract class ValueFormatter {

	// 시간 포맷
	public String timestampToString(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		return sdf.format(timestamp);
	}

	public String formatKoreanWon(Long amount) {
		// 123456 -> 123,456 으로 표기하기 위함
		DecimalFormat df = new DecimalFormat("#, ###");
		String formatNumber = df.format(amount);
		return formatNumber + " 원"; 
	}
	
}
