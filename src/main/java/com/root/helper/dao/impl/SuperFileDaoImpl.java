package com.root.helper.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.root.helper.dao.BaseDao;
import com.root.helper.dao.ISuperFileDao;
import com.root.helper.entity.SuperFile;
import com.root.helper.mapper.ISuperFileMapper;
import com.root.helper.param.SuperFileQueryParam;

@Repository
public class SuperFileDaoImpl extends BaseDao<ISuperFileMapper> implements ISuperFileDao {
	
	@Override
	public int save(SuperFile param) {
		ISuperFileMapper mapper = getMapper(ISuperFileMapper.class);
		return mapper.save(param);
	}

	@Override
	public int update(SuperFile param) {
		ISuperFileMapper mapper = getMapper(ISuperFileMapper.class);
		return mapper.update(param);
	}

	@Override
	public int deleteById(String id) {
		ISuperFileMapper mapper = getMapper(ISuperFileMapper.class);
		return mapper.deleteById(id);
	}

	@Override
	public SuperFile getDataById(String id) {
		ISuperFileMapper mapper = getMapper(ISuperFileMapper.class);
		return mapper.getDataById(id);
	}

	@Override
	public int count(SuperFileQueryParam param) {
		ISuperFileMapper mapper = getMapper(ISuperFileMapper.class);
		return mapper.count(param);
	}

	@Override
	public List<SuperFile> query(SuperFileQueryParam param, int start, int end) {
		ISuperFileMapper mapper = getMapper(ISuperFileMapper.class);
		return mapper.query(param, start, end);
	}
}