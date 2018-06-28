package com.spring.boot.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.model.JsInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HtmlController {
	@GetMapping("/html/list")
	public String htmlList(HttpServletRequest request, ModelMap modelMap) {
		log.info("htmlList");
		
		ArrayList arrayList = new ArrayList();
		
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			System.out.println("Current relative path is: " + s);
			
			Path rootDir = Paths.get("src/main/resources/static/html");
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
					String replacePath = orgPath.replace("src\\main\\resources\\static\\html\\", "");
					log.info("replacePath : {}", replacePath);
					
					JsInfo jsInfo = new JsInfo();
					jsInfo.setName(item.getFileName().toString());
					jsInfo.setPath(replacePath);
					arrayList.add(jsInfo);
				});
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		log.info("htmlList : {}", arrayList);
		
		modelMap.addAttribute("page", "fragments/html/htmlList");
		modelMap.addAttribute("list", arrayList);
		
		return "index";
	}
	
	@GetMapping("/html/list/action")
	public String htmlAction(HttpServletRequest request, @RequestParam("cars") List cars, ModelMap modelMap) {
		log.info("request : {}", request.getParameter("cars"));
		log.info("@RequestParam List : {}", cars);
		
		modelMap.addAttribute("page", "fragments/html/htmlList");
		
		return "index";
	}
}
