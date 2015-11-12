package com.root.helper.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.root.helper.entity.SuperFile;
import com.root.helper.param.SuperFileQueryParam;

public interface ISuperFileMapper {
	
	int save(@Param("param") SuperFile param);
	int update(@Param("param") SuperFile param);
	int deleteById(@Param("id") String id);
	SuperFile getDataById(@Param("id") String id);
	
	int count(@Param("param") SuperFileQueryParam param);
	List<SuperFile> query(@Param("param") SuperFileQueryParam param,
			@Param("start") int start, @Param("end") int end);
}