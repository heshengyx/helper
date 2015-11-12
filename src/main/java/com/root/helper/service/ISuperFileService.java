package com.root.helper.service;

import com.root.helper.entity.SuperFile;
import com.root.helper.page.IPage;
import com.root.helper.param.SuperFileQueryParam;

public interface ISuperFileService {

	void save(SuperFile param);
	void update(SuperFile param);
	void deleteById(String id);
	SuperFile getDataById(String id);
	
	IPage<SuperFile> query(SuperFileQueryParam param, int page, int rows);
}