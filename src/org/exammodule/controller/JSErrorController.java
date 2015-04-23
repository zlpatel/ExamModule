package org.exammodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JSErrorController {
	@RequestMapping("/jserror")
	public String getEnableJSPage() {
		return "enablejs";
	}
}
