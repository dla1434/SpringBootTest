package com.spring.boot.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.model.JsInfo;
import com.spring.boot.model.PagingVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DataTableController {
	@GetMapping("/datatable/list/view")
	public String datatableListView(HttpServletRequest request, ModelMap modelMap) {
		log.info("datatable");
		
		modelMap.addAttribute("page", "fragments/dataTable/dataTableList");
		
		return "index";
	}
	
	@GetMapping("/datatable/list")
	@ResponseBody
	public ArrayList datatableList(HttpServletRequest request, PagingVo pagingVo) {
		log.info("datatableList");
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
		
		return arrayList;
	}
	
	@GetMapping("/datatable/list/serverSide/view")
	public String datatableServerSideListView(HttpServletRequest request, ModelMap modelMap) {
		log.info("datatableServerSideListView");
		
//		modelMap.addAttribute("page", "fragments/dataTable/dataTableList");
		
		return "fragments/dataTable/dataTableServerSideList";
	}
	
	@GetMapping("/datatable/serverSide/list")
	@ResponseBody
	public PagingVo datatableServerSideList(HttpServletRequest request, PagingVo pagingVo) {
		log.info("datatableServerSideList");
		
		int offset  = Integer.parseInt(request.getParameter("start"));
		int length =  Integer.parseInt(request.getParameter("length"));
		pagingVo.setOffset(offset);
		pagingVo.setLength(length);
		
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
		
		pagingVo.setRecordsTotal(arrayList.size());
		pagingVo.setRecordsFiltered(arrayList.size());
		pagingVo.setData(arrayList);
		
		return pagingVo;
	}
	
	@GetMapping("/datatable/thymeleaf/list")
	public String dataTableThymeleafList(HttpServletRequest request, ModelMap modelMap) {
		log.info("dataTableThymeleafList");
		
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
		
		modelMap.addAttribute("page", "fragments/dataTable/dataTableThymeleafList");
		modelMap.addAttribute("dataTableList", arrayList);
		
		return "index";
	}
}
