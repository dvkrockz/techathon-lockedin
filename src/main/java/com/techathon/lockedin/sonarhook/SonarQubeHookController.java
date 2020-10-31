package com.techathon.lockedin.sonarhook;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sonarqubewebhook")
public class SonarQubeHookController {

	
	@PostMapping(value="postsonarqubedata")
	public String sonarQubeWebHookData(@RequestBody String jsonObject) throws ParseException {
		JSONParser parser = new JSONParser(jsonObject); 
//		System.out.println(parser.parseObject().get("total"));
		System.out.println(jsonObject);
		return jsonObject;
	}
}
