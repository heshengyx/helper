package com.root.helper.dao;

import java.util.List;
import com.root.helper.entity.Pad;
import com.root.helper.param.PadQueryParam;

public interface IPadDao {
	
	int save(Pad param);
	int update(Pad param);
	int deleteById(String id);
	Pad getDataById(String id);
	
	int count(PadQueryParam param);
	List<Pad> query(PadQueryParam param, int start, int end);
}
