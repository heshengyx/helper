package com.root.helper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.root.helper.entity.SuperFile;
import com.root.helper.param.SuperFileQueryParam;
import com.root.helper.service.ISuperFileService;
import com.myself.common.exception.ServiceException;
import com.myself.common.message.JsonMessage;

@Controller
@RequestMapping("/manage/superFile")
public class SuperFileManageController extends BaseController {

	private final static Logger logger = LoggerFactory
			.getLogger(SuperFileManageController.class);
	
	@Autowired
	private ISuperFileService superFileService;
	
	@RequestMapping("")
	public String page() {
		return "superFile-list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "superFile-add";
	}
	
	@RequestMapping("/edit")
	public String edit() {
		return "superFile-edit";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(SuperFileQueryParam param, int page, int rows) {
		return superFileService.query(param, page, rows);
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public Object getData(String id) {
		JsonMessage jMessage = new JsonMessage();
		SuperFile data = null;
		try {
			data = superFileService.getDataById(id);
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
	public Object save(SuperFile param) {
		JsonMessage jMessage = new JsonMessage();
		try {
			superFileService.save(param);
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
	public Object update(SuperFile param) {
		JsonMessage jMessage = new JsonMessage();
		try {
			superFileService.update(param);
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
			superFileService.deleteById(id);
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
}
