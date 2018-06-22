package com.spring.boot.dir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PrintFilelist {

	@Test
	public void contextLoads() {
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			System.out.println("Current relative path is: " + s);
			
			Path rootDir = Paths.get("src/main/resources/static/js");
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
					String replacePath = orgPath.replace("src\\main\\resources\\static\\", "");
					log.info("replacePath : {}", replacePath);
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
