package com.test.collector.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CollectorRepository {
	
	private static final String namespace = "com.test.mapper.collectorMapper";

	@Autowired
	private SqlSession sqlSession;
	
	
}
