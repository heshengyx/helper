package com.root.helper.dao;

import java.util.List;
import com.root.helper.entity.Attribute;
import com.root.helper.param.AttributeQueryParam;

public interface IAttributeDao {
	
	int save(Attribute param);
	int update(Attribute param);
	int deleteById(String id);
	Attribute getDataById(String id);
	
	int count(AttributeQueryParam param);
	List<Attribute> query(AttributeQueryParam param, int start, int end);
}
