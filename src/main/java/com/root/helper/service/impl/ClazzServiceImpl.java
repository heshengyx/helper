package com.root.helper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.common.exception.ServiceException;
import com.myself.common.utils.UIDGeneratorUtil;
import com.root.helper.dao.IAttributeDao;
import com.root.helper.dao.IClazzDao;
import com.root.helper.data.ClazzAttribute;
import com.root.helper.entity.Attribute;
import com.root.helper.entity.Clazz;
import com.root.helper.page.IPage;
import com.root.helper.page.Page;
import com.root.helper.param.ClazzQueryParam;
import com.root.helper.service.IClazzService;

@Service("clazzService")
public class ClazzServiceImpl implements IClazzService {

	@Autowired
	private IClazzDao clazzDao;
	
	@Autowired
	private IAttributeDao attributeDao;
	
	@Override
	public void save(Clazz param) {
		param.setId(UIDGeneratorUtil.getUID());
		param.setStatus("1");
		param.setCreateTime(new Date());
		int count = clazzDao.save(param);
		if (count < 1) {
			throw new ServiceException("新增失败");
		}
	}

	@Override
	public void update(Clazz param) {
		param.setUpdateTime(new Date());
		int count = clazzDao.update(param);
		if (count < 1) {
			throw new ServiceException("更新失败");
		}
	}

	@Override
	public void deleteById(String id) {
		int count = clazzDao.deleteById(id);
		if (count < 1) {
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public Clazz getDataById(String id) {
		return clazzDao.getDataById(id);
	}

	@Override
	public IPage<Clazz> query(ClazzQueryParam param, int page, int rows) {
		List<Clazz> list = null;
		page = page <= 0 ? 1 : page;
		rows = rows <= 0 ? 10 : rows;
		int count = clazzDao.count(param);
		if (count > 0) {
			int start = (page - 1) * rows;
			int end = start + rows;
			list = clazzDao.query(param, start, end);
		}
		return new Page<Clazz>(list, count, page, rows);
	}

	@Override
	@Transactional
	public void save(ClazzAttribute param) {
		Clazz clazz = new Clazz();
		clazz.setClazzName(param.getClazzName());
		clazz.setTableName(param.getTableName());
		clazz.setTitle(param.getTitle());
		save(clazz);
		
		List<Attribute> attributes = new ArrayList<Attribute>();
		String[] attributeNames = param.getAttributeName();
		String[] attributeTitles = param.getAttributeTitle();
		String[] attributeTypes = param.getAttributeType();
		for (int i = 0; i < attributeNames.length; i++) {
			Attribute attribute = new Attribute();
			attribute.setAttributeName(attributeNames[i]);
			attribute.setAttributeTitle(attributeTitles[i]);
			attribute.setAttributeType(attributeTypes[i]);
			attribute.setId(UIDGeneratorUtil.getUID());
			attribute.setClazzId(clazz.getId());
			attribute.setStatus("1");
			attribute.setCreateTime(new Date());
			attributeDao.save(attribute);
			//attributes.add(attribute);
		}
	}
}
