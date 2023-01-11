package com.test.collector.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.test.collector.repository.CollectorRepository;
import com.test.collector.service.CollectorService;

@RestController
@RequestMapping("/students")
public class CollectorController {

	private CollectorService collectorService;

	@ResponseBody
	@RequestMapping("/register")
	public Integer register(@RequestParam Map<String, Object> map) throws Exception {

		System.out.println("controller - register" + map);

		return collectorService.register(map);

	}

	@ResponseBody
	@RequestMapping("/update")
	public Integer update(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("controller - update " + map);

		return collectorService.update(map);
	}

	@ResponseBody
	@RequestMapping("/select")
	public Integer select(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("controller - select " + map);

		return collectorService.selectOne(map);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("controller - delete " + map);

		return collectorService.delete(map);
	}


	// controller -> 요청 주소 정의 및 요청 파라미터 확인
	// service -> 실제 로직 처리
	// repository -> 데이터베이스에 실행시키는 쿼리부분과 연결
	// mapper -> 실제 데이터베이스에 실행시키는 쿼리의 모음
	// 과제 4개 순서대로 연결하고 - 셀렉트1

	// 스프링 빈 / DI / AOP / 		HTTP
}
