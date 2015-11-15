package com.root.helper.service;

import com.root.helper.entity.Attribute;
import com.root.helper.page.IPage;
import com.root.helper.param.AttributeQueryParam;

public interface IAttributeService {

	void save(Attribute param);
	void update(Attribute param);
	void deleteById(String id);
	Attribute getDataById(String id);
	
	IPage<Attribute> query(AttributeQueryParam param, int page, int rows);
}
