package com.root.helper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myself.common.exception.ServiceException;
import com.myself.common.utils.UIDGeneratorUtil;
import com.root.helper.dao.IPadDao;
import com.root.helper.entity.Pad;
import com.root.helper.page.IPage;
import com.root.helper.page.Page;
import com.root.helper.param.PadQueryParam;
import com.root.helper.service.IPadService;

@Service("padService")
public class PadServiceImpl implements IPadService {

	@Autowired
	private IPadDao padDao;

	@Override
	public void save(Pad param) {
		param.setId(UIDGeneratorUtil.getUID());
		param.setStatus("1");
		param.setCreateTime(new Date());
		int count = padDao.save(param);
		if (count < 1) {
			throw new ServiceException("新增失败");
		}
	}

	@Override
	public void update(Pad param) {
		param.setUpdateTime(new Date());
		int count = padDao.update(param);
		if (count < 1) {
			throw new ServiceException("更新失败");
		}
	}

	@Override
	public void deleteById(String id) {
		int count = padDao.deleteById(id);
		if (count < 1) {
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public Pad getDataById(String id) {
		return padDao.getDataById(id);
	}

	@Override
	public IPage<Pad> query(final PadQueryParam param, int page, int rows) {
		List<Pad> list = null;
		page = page <= 0 ? 1 : page;
		rows = rows <= 0 ? 10 : rows;
		int count = padDao.count(param);
		if (count > 0) {
			int start = (page - 1) * rows;
			int end = start + rows;
			list = padDao.query(param, start, end);
		}
		return new Page<Pad>(list, count, page, rows);

	}

	@Override
	public Map<String, Object> export(PadQueryParam param) {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<String[]> excel = new ArrayList<String[]>();

		final String[] cellsTitle = new String[] { "序号", "名称"};
		/*final Map<Integer, String[]> titles = new HashMap<Integer, String[]>(); //报表标题合并
		titles.put(1, new String[] {"14", "银行到账信息"});
		titles.put(15, new String[] {"11", "认领结果"});*/
		final Map<Integer, String> header = new HashMap<Integer, String>(); //报表头部
		header.put(cellsTitle.length, "信息内容");

		String[] cells = null;
		int length = cellsTitle.length;
		int row = 0;
		List<Pad> list = padDao.query(param, 0, Integer.MAX_VALUE);

		for (Pad data : list) {
			cells = new String[length];
			int i = 0;
			cells[i++] = String.valueOf(row); // 序号
			cells[i++] = data.getName(); // 公司代码
			excel.add(cells);
			row++;
		}
		map.put("sheetName", "sheet1");// sheet名 必填
		map.put("data", excel);
		map.put("header", header); // 统计头部
		//map.put("titles", titles); //头部标题
		map.put("cellsTitle", cellsTitle);
		map.put("fileName", "信息内容_");
		return map;
	}
}
