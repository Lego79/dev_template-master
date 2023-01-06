package com.test.collector.repository;

import com.test.collector.config.database.DatabaseConfig;
import com.test.collector.dto.CollectorDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository
public class CollectorRepository {

	private static final String namespace = "com.test.mapper.collectorMapper";




	@Autowired
	private SqlSession sqlSession;

	public Integer register(Map<String, Object> map) throws Exception {
		return sqlSession.insert(namespace + ".register", map);
	}
	public Integer update(Map<String, Object> map) throws Exception {
		return sqlSession.update(namespace + ".update", map);
	}

	public Integer selectOne(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".select", map);
	}

	public Integer delete(Map<String, Object> map) throws Exception {
		return sqlSession.delete(namespace + ".delete", map);
	}

	//CRUD
}
