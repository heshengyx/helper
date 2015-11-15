package com.root.helper.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.helper.dao.IAttributeDao;
import com.root.helper.entity.Attribute;
import com.root.helper.page.IPage;
import com.root.helper.page.Page;
import com.root.helper.service.IAttributeService;
import com.root.helper.param.AttributeQueryParam;
import com.myself.common.exception.ServiceException;
import com.myself.common.utils.UIDGeneratorUtil;

@Service("attributeService")
public class AttributeServiceImpl implements IAttributeService {

	@Autowired
	private IAttributeDao attributeDao;
	
	@Override
	public void save(Attribute param) {
		param.setId(UIDGeneratorUtil.getUID());
		param.setStatus("1");
		param.setCreateTime(new Date());
		int count = attributeDao.save(param);
		if (count < 1) {
			throw new ServiceException("新增失败");
		}
	}

	@Override
	public void update(Attribute param) {
		param.setUpdateTime(new Date());
		int count = attributeDao.update(param);
		if (count < 1) {
			throw new ServiceException("更新失败");
		}
	}

	@Override
	public void deleteById(String id) {
		int count = attributeDao.deleteById(id);
		if (count < 1) {
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public Attribute getDataById(String id) {
		return attributeDao.getDataById(id);
	}

	@Override
	public IPage<Attribute> query(AttributeQueryParam param, int page, int rows) {
		List<Attribute> list = null;
		page = page <= 0 ? 1 : page;
		rows = rows <= 0 ? 10 : rows;
		int count = attributeDao.count(param);
		if (count > 0) {
			int start = (page - 1) * rows;
			int end = start + rows;
			list = attributeDao.query(param, start, end);
		}
		return new Page<Attribute>(list, count, page, rows);
	}
}
