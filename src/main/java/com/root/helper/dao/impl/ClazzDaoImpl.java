package com.root.helper.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.root.helper.dao.BaseDao;
import com.root.helper.dao.IClazzDao;
import com.root.helper.entity.Clazz;
import com.root.helper.mapper.IClazzMapper;
import com.root.helper.param.ClazzQueryParam;

@Repository
public class ClazzDaoImpl extends BaseDao<IClazzMapper> implements IClazzDao {
	
	@Override
	public int save(Clazz param) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.save(param);
	}

	@Override
	public int update(Clazz param) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.update(param);
	}

	@Override
	public int deleteById(String id) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.deleteById(id);
	}

	@Override
	public Clazz getDataById(String id) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.getDataById(id);
	}

	@Override
	public int count(ClazzQueryParam param) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.count(param);
	}

	@Override
	public List<Clazz> query(ClazzQueryParam param, int start, int end) {
		IClazzMapper mapper = getMapper(IClazzMapper.class);
		return mapper.query(param, start, end);
	}
}
