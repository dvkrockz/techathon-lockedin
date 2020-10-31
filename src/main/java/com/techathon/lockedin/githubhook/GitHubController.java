package com.techathon.lockedin.githubhook;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping(value = "githubwebhook")
public class GitHubController {

	@GetMapping("getOk")
	public String getData(){
		return "ok";
		}
	
	@PostMapping(value="postgithubdata")
	public String gitHubWebHook(@RequestBody String jsonObject) throws ParseException {
		JSONParser parser = new JSONParser(jsonObject); 
		System.out.println(parser.parseObject().get("total"));
		return parser.parseObject().toString();
	}
	
}
