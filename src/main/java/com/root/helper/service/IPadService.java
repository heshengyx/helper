package com.root.helper.service;

import java.util.Map;

import com.root.helper.entity.Pad;
import com.root.helper.page.IPage;
import com.root.helper.param.PadQueryParam;

public interface IPadService {

	void save(Pad param);
	void update(Pad param);
	void deleteById(String id);
	Pad getDataById(String id);
	
	IPage<Pad> query(PadQueryParam param, int page, int rows);
	Map<String, Object> export(PadQueryParam param);
}
