package com.root.helper.service;

import com.root.helper.data.ClazzAttribute;
import com.root.helper.entity.Clazz;
import com.root.helper.page.IPage;
import com.root.helper.param.ClazzQueryParam;

public interface IClazzService {

	void save(Clazz param);
	void update(Clazz param);
	void deleteById(String id);
	Clazz getDataById(String id);
	
	void save(ClazzAttribute param);
	IPage<Clazz> query(ClazzQueryParam param, int page, int rows);
}
