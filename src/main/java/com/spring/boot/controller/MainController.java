package com.spring.boot.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.model.JsInfo;
import com.spring.boot.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/")
	public String home(HttpServletRequest request, ModelMap modelMap) {
		log.info("Web Server Root Start");
		
		modelMap.addAttribute("page", "fragments/main");
		
		return "index";
	}
	
	/* DataTable Version
	@GetMapping("/js/list/view")
	public String jsListView(HttpServletRequest request, ModelMap modelMap) {
		log.info("jsListView");
		
		modelMap.addAttribute("page", "fragments/js/jsList");
		
		return "index";
	}
	
	@GetMapping("/js/list")
	@ResponseBody
	public PagingVo jsList(HttpServletRequest request, PagingVo pagingVo) throws JsonGenerationException, JsonMappingException, IOException {
		log.info("jsList");
		
		int offset  = Integer.parseInt(request.getParameter("start"));
		int length =  Integer.parseInt(request.getParameter("length"));
		pagingVo.setOffset(offset);
		pagingVo.setLength(length);
		
		ArrayList arrayList = new ArrayList();
		
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			log.debug("Current relative path is: {}", s);
			
			Path rootDir = Paths.get("src/main/resources/static/js");
			log.debug("rootDir : {}, rootDir.toFile() : {}", rootDir, rootDir.toFile());
			
			Files.walk(rootDir)
				.filter(Files::isRegularFile)
				.filter(file -> {
					String fileName = file.getFileName().toString();
//					log.info("fileName : {}", fileName);
					return fileName.endsWith(".html");
				})
				.forEach(item -> {
					String orgPath = item.toString();
					String replacePath = orgPath.replace("src\\main\\resources\\static\\js\\", "");
					log.debug("replacePath : {}", replacePath);
					
					JsInfo jsInfo = new JsInfo();
					jsInfo.setName(item.getFileName().toString());
					jsInfo.setPath(replacePath);
					arrayList.add(jsInfo);
				});
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		log.info("jsList : {}", arrayList);
		
		pagingVo.setRecordsTotal(arrayList.size());
		pagingVo.setRecordsFiltered(arrayList.size());
		pagingVo.setData(arrayList);
		
		return pagingVo;
//		return JsonUtil.marshallingJson(pagingVo);
	}
	*/
	
	@GetMapping("/datatable/list")
	public String dataTableList(HttpServletRequest request, ModelMap modelMap) {
		log.info("dataTableList");
		
		ArrayList arrayList = new ArrayList();
		
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			System.out.println("Current relative path is: " + s);
			
			Path rootDir = Paths.get("src/main/resources/static/dataTable");
			log.info("rootDir : {}, rootDir.toFile() : {}", rootDir, rootDir.toFile());
			
			Files.walk(rootDir)
				.filter(Files::isRegularFile)
				.filter(file -> {
					String fileName = file.getFileName().toString();
//					log.info("fileName : {}", fileName);
					return fileName.endsWith(".html");
				})
				.forEach(item -> {
					String orgPath = item.toString();
					String replacePath = orgPath.replace("src\\main\\resources\\static\\dataTable\\", "");
					log.info("replacePath : {}", replacePath);
					
					JsInfo jsInfo = new JsInfo();
					jsInfo.setName(item.getFileName().toString());
					jsInfo.setPath(replacePath);
					arrayList.add(jsInfo);
				});
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		log.info("jsList : {}", arrayList);
		
		modelMap.addAttribute("page", "fragments/js/jsList");
		modelMap.addAttribute("jsList", arrayList);
		
		return "index";
	}
	
	@GetMapping("/new-task")
	public String newTask(HttpServletRequest request, ModelMap modelMap) {
		log.info("newTask");
		
		modelMap.addAttribute("page", "fragments/task/taskView");
		
		return "index";
	}
	
	@GetMapping("/update-task")
	public String updateTask(HttpServletRequest request, ModelMap modelMap, @RequestParam int id) {
		log.info("newTask");
		
		modelMap.addAttribute("task", taskService.findTask(id));
		modelMap.addAttribute("page", "fragments/task/taskView");
		
		return "index";
	}
}
