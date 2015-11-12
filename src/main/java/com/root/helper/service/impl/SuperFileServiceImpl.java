package com.root.helper.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.helper.dao.ISuperFileDao;
import com.root.helper.entity.SuperFile;
import com.root.helper.page.IPage;
import com.root.helper.page.Page;
import com.root.helper.service.ISuperFileService;
import com.root.helper.param.SuperFileQueryParam;
import com.myself.common.exception.ServiceException;
import com.myself.common.utils.UIDGeneratorUtil;

@Service("superFileService")
public class SuperFileServiceImpl implements ISuperFileService {

	@Autowired
	private ISuperFileDao superFileDao;
	
	@Override
	public void save(SuperFile param) {
		param.setId(UIDGeneratorUtil.getUID());
		param.setStatus("1");
		param.setCreateTime(new Date());
		int count = superFileDao.save(param);
		if (count < 1) {
			throw new ServiceException("新增失败");
		}
	}

	@Override
	public void update(SuperFile param) {
		param.setUpdateTime(new Date());
		int count = superFileDao.update(param);
		if (count < 1) {
			throw new ServiceException("更新失败");
		}
	}

	@Override
	public void deleteById(String id) {
		int count = superFileDao.deleteById(id);
		if (count < 1) {
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public SuperFile getDataById(String id) {
		return superFileDao.getDataById(id);
	}

	@Override
	public IPage<SuperFile> query(SuperFileQueryParam param, int page, int rows) {
		List<SuperFile> list = null;
		page = page <= 0 ? 1 : page;
		rows = rows <= 0 ? 10 : rows;
		int count = superFileDao.count(param);
		if (count > 0) {
			int start = (page - 1) * rows;
			int end = start + rows;
			list = superFileDao.query(param, start, end);
		}
		return new Page<SuperFile>(list, count, page, rows);
	}
}
