package com.root.helper.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.root.helper.entity.Pad;
import com.root.helper.param.PadQueryParam;

public interface IPadMapper {
	
	int save(@Param("param") Pad param);
	int update(@Param("param") Pad param);
	int deleteById(@Param("id") String id);
	Pad getDataById(@Param("id") String id);
	
	int count(@Param("param") PadQueryParam param);
	List<Pad> query(@Param("param") PadQueryParam param,
			@Param("start") int start, @Param("end") int end);
}
