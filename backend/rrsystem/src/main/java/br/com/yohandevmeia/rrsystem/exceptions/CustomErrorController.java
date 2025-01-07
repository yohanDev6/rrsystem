package br.com.yohandevmeia.rrsystem.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	    if (status != null) {
	        int statusCode = Integer.parseInt(status.toString());

	        model.addAttribute("status", statusCode);

	        if (statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "errors/notfound";
	        } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
	            return "errors/badrequest";
	        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
	            return "errors/unauthorized";
	        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "errors/internalservererror";
	        }
	    }
	    return "errors/error";
	}
}
