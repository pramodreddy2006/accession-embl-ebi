package com.ebi.accession.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ebi.accession.util.AccessionUtil;

@Controller
@RequestMapping(value = "/accession")
public class AccessionController {

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, value = "/array")
	public @ResponseBody List<String> getOrderRanges(@RequestBody String[] elements) throws Exception {
		List<String> sol = AccessionUtil.getOrderedRangeList(elements);
		return sol;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, value = "")
	public @ResponseBody List<String> getOrderRanges(@RequestBody String input) throws Exception {
		String[] elements = input.split(",");
		List<String> sol = AccessionUtil.getOrderedRangeList(elements);
		return sol;
	}

}
