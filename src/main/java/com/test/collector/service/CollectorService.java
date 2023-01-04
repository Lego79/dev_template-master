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
		System.out.println("[SERVICE]"  + map );
		// 빨간 책일때는 책이름 앞에 [빨강] ㅡ을 붙여라

		return collectorRepository.register(map);
	}
	public Integer update(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println("[SERVICE]"  + map );
		// 빨간 책일때는 책이름 앞에 [빨강] ㅡ을 붙여라

		return collectorRepository.update(map);
	}
/*
책 삭제 api개발
책 select api 개발

HTTP 통신
RESTFUL API



-> 코인 API
현재 시세조회,
판매,
구매, -> 현재가 ~~가


 */
}

