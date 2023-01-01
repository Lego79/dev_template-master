package com.test.collector.repository;

import com.test.collector.config.database.DatabaseConfig;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class CollectorRepository {
	
	private static final String namespace = "com.test.mapper.collectorMapper";

	List<DatabaseConfig>

	@Autowired
	private SqlSession sqlSession;
	
	
}
