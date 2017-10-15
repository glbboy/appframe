package com.cnframe.web.charge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnframe.web.BaseController;

@Controller
@RequestMapping("/charge/")
public class ChargeMainController extends BaseController{
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("charge index");
        return "/charge/index";
    }
}
