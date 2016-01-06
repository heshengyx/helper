package com.root.helper.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myself.common.exception.ServiceException;
import com.myself.common.message.JsonMessage;
import com.root.helper.entity.Pad;
import com.root.helper.param.PadQueryParam;
import com.root.helper.service.IPadService;
import com.root.helper.view.ExcelView;

@Controller
@RequestMapping("/manage/pad")
public class PadManageController extends BaseController {

	private final static Logger logger = LoggerFactory
			.getLogger(PadManageController.class);
	
	@Autowired
	private IPadService padService;
	
	@RequestMapping("")
	public String page() {
		return "pad-list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "pad-add";
	}
	
	@RequestMapping("/edit")
	public String edit() {
		return "pad-edit";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(PadQueryParam param, int page, int rows) {
		return padService.query(param, page, rows);
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public Object getData(String id) {
		JsonMessage jMessage = new JsonMessage();
		Pad data = null;
		try {
			data = padService.getDataById(id);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setData(data);
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Object save(Pad param) {
		JsonMessage jMessage = new JsonMessage();
		try {
			padService.save(param);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setMessage("新增成功");
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(Pad param) {
		JsonMessage jMessage = new JsonMessage();
		try {
			padService.update(param);
			jMessage.setCode(JsonMessage.SUCCESS_CODE);
			jMessage.setMessage("更新成功");
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		JsonMessage jMessage = new JsonMessage();
		try {
			padService.deleteById(id);
		} catch (Exception e) {
			jMessage.setCode(JsonMessage.ERROR_CODE);
			if (e instanceof ServiceException) {
				jMessage.setMessage(e.getMessage());
			} else {
				jMessage.setMessage("系统异常");
			}
			logger.error(jMessage.getMessage(), e);
		}
		return jMessage;
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public ModelAndView export(PadQueryParam param) {
		Map<String, Object> map = null;
		try {
			map = padService.export(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(new ExcelView(), map);
	}
}