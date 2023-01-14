package com.test.collector.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ValidationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.test.collector.repository.CollectorRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Service
public class CollectorService {

	@Autowired
	private CollectorRepository collectorRepository;



	public Integer register(@RequestParam Map<String, Object> map) throws Exception {



		/*
		인풋
		map
		- 코인 구매자 정보 (지갑정보)
		- 코인 종류
		- 금액
		- ex) 현재가 구매여부 ...

		행위
		- 해당 고객이 잔액이 구매금액보다 많을 경우에만 구매요청
			아닐경우 잔액부족으로 리턴
		- 바이낸스 코인 구매 api에 구매 요청
		- 구매 성공시 잔액 저장
		- 구매 성공/실패 이력 저장

		아웃풋
		- 실패했을경우 정확한 실패 사유 전달 (금액이 없거나, 지갑정보가 잘못되었거나)
		- 성공했을경우 잔액과 코인 보유현황을 리턴
		 */
		String walletId = String.valueOf("walletId");
		String coinType = String.valueOf("coinType");
		String price  = String.valueOf("price");
		String amount  = String.valueOf("amount");
		String userIid = String.valueOf("price");
		/*
		select balance from coin_user where user_id = #{userId}
		 */
		HashMap<String, Object> map2 = collectorRepository.selectBalance(userId);
		Integer balance = Intger.valueOf(String.valueOf(map2.get("balance")));

		if (balance >= Integer.valueOf(price)*Integer.valueOf(amount))	{
			// api 호출
			//~~ 테이블에 ~~를 넣는다
		}	else {
			// 잔액부족 리턴
		}

		String result ="";
		// result = 구매 api 요청 응답값
		// result = "200";

		return collectorRepository.register(map);
	}
	public Integer update(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("[SERVICE]"  + map );
		/*
		인풋
		map
		- 코인 구매자 정보 (지갑정보)
		- 코인 종류
		- 금액
		- ex) 현재가 핀매여부 ...

		행위
		- 바이낸스 코인 구매 api에 판매 요청
		- 판매 성공시 잔액 저장
		- 판매 성공/실패 이력 저장

		아웃풋
		- 실패했을경우 정확한 실패 사유 전달 (금액이 없거나, 지갑정보가 잘못되었거나)
		- 성공했을경우 잔액과 코인 보유현황을 리턴
		 */



		return collectorRepository.update(map);
	}


	public Integer selectOne(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("[SERVICE]"  + map );
/*
		인풋
		map
		- 코인 종류

		행위
		- 바이낸스 코인 구매 api에서 현재 시세 얼만지 요청
		- 현재 금액 저장
		-  성공/실패 이력 저장

		아웃풋
		- 실패했을경우 정확한 실패 사유 전달
		- 성공했을경우 현재 시세 리턴
		 */

		return collectorRepository.selectOne(map);
	}

	public Integer delete(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("[SERVICE]"  + map );
		return collectorRepository.delete(map);
	}
/*
-> 코인 API
현재 시세조회,
판매,
구매

판매 api
	판매 후 판매 이력 저장
구매 api
	구매 후 구매이력 저장
시세조회 api
	현지 시세 저장
잔액조회 api
	고객의 구매이력 및 잔액을 리턴

create table coin_user	(

coin_user
	- user_id			1
	- balance

coin_wallet
	- user_id			1
	- coin_type			B
	- coin_amount		100


coin_buy_history
	- wallet_id
	- coin_type
	- coin_price
	- reg_dt
	- reg_user

coin_sell_history
	- wallet_id
	- coin_type
	- coin_price
	- reg_dt
	- reg_user


coin_status
	- coin_type
	- coin_price
	- reg_dt

coin_api_call_hist
	- api_type (S/B/)
	- api_result_Cd
	- api_result_msg
	- reg_dt


사용자
- 사용자정보
- 현금

지갑
- 지갑 주인(사용자)
- 코인 종류
- 보유 코인

구매
- 지갑정보
- 코인종류
- 구매 금액
- 구매 시간
- 구매한 사람

판매
- 지갑정보
- 코인종류
- 판매 금액
- 판매 시간
- 판매한 사람

조회
- 코인종류
- 코인 금액
- 조회시간


api 호출 이력
- api종류 (구매//판매//조회)
- api 호출 결과
- api 호출 결과 사유
- api 호출 시간





 */
}

