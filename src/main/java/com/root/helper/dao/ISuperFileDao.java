package com.root.helper.dao;

import java.util.List;
import com.root.helper.entity.SuperFile;
import com.root.helper.param.SuperFileQueryParam;

public interface ISuperFileDao {
	
	int save(SuperFile param);
	int update(SuperFile param);
	int deleteById(String id);
	SuperFile getDataById(String id);
	
	int count(SuperFileQueryParam param);
	List<SuperFile> query(SuperFileQueryParam param, int start, int end);
}