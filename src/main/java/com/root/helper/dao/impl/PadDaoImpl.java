package com.root.helper.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.root.helper.dao.BaseDao;
import com.root.helper.dao.IPadDao;
import com.root.helper.entity.Pad;
import com.root.helper.mapper.IPadMapper;
import com.root.helper.param.PadQueryParam;

@Repository
public class PadDaoImpl extends BaseDao<IPadMapper> implements IPadDao {
	
	@Override
	public int save(Pad param) {
		IPadMapper mapper = getMapper(IPadMapper.class);
		return mapper.save(param);
	}

	@Override
	public int update(Pad param) {
		IPadMapper mapper = getMapper(IPadMapper.class);
		return mapper.update(param);
	}

	@Override
	public int deleteById(String id) {
		IPadMapper mapper = getMapper(IPadMapper.class);
		return mapper.deleteById(id);
	}

	@Override
	public Pad getDataById(String id) {
		IPadMapper mapper = getMapper(IPadMapper.class);
		return mapper.getDataById(id);
	}

	@Override
	public int count(PadQueryParam param) {
		IPadMapper mapper = getMapper(IPadMapper.class);
		return mapper.count(param);
	}

	@Override
	public List<Pad> query(PadQueryParam param, int start, int end) {
		IPadMapper mapper = getMapper(IPadMapper.class);
		return mapper.query(param, start, end);
	}
}
