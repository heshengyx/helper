package com.root.helper.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.root.helper.dao.BaseDao;
import com.root.helper.dao.IAttributeDao;
import com.root.helper.entity.Attribute;
import com.root.helper.mapper.IAttributeMapper;
import com.root.helper.param.AttributeQueryParam;

@Repository
public class AttributeDaoImpl extends BaseDao<IAttributeMapper> implements IAttributeDao {
	
	@Override
	public int save(Attribute param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.save(param);
	}

	@Override
	public int update(Attribute param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.update(param);
	}

	@Override
	public int deleteById(String id) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.deleteById(id);
	}

	@Override
	public Attribute getDataById(String id) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.getDataById(id);
	}

	@Override
	public int count(AttributeQueryParam param) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.count(param);
	}

	@Override
	public List<Attribute> query(AttributeQueryParam param, int start, int end) {
		IAttributeMapper mapper = getMapper(IAttributeMapper.class);
		return mapper.query(param, start, end);
	}
}
