package com.spring.boot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	public void htmlAction(HttpServletRequest request, HttpServletResponse response, @RequestParam("feature") List feature, ModelMap modelMap) throws IOException {
		log.info("request : {}", request.getParameter("feature"));
		log.info("@RequestParam List : {}", feature);
		
//		String strFeature = Arrays.asList(feature).stream()
//			.map(n -> n.toString())
//			.collect( Collectors.joining("|"));
		log.info("StringJoin : {}", String.join(",", feature));
		
		response.sendRedirect("/html/list");
	}
}
