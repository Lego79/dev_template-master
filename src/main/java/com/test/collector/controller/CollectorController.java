package com.test.collector.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.collector.repository.CollectorRepository;
import com.test.collector.service.CollectorService;

@Controller
@RequestMapping("/book")
public class CollectorController {
	
	@Autowired
	private CollectorService collectorService;
	
	@RequestMapping("/register")
	public String test(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("[CONTROLLER]" + map);
		return collectorService.test(map);
	}

	// controller -> 요청 주소 정의 및 요청 파라미터 확인
	// service -> 실제 로직 처리
	// repository -> 데이터베이스에 실행시키는 쿼리부분과 연결
	// mapper -> 실제 데이터베이스에 실행시키는 쿼리의 모음
	// 과제 4개 순서대로 연결하고 - 셀렉트1

	// 스프링 빈 / DI / AOP / 		HTTP
} 
