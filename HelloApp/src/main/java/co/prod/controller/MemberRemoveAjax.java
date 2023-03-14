package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;
import co.prod.service.MemberService;
import co.prod.service.MemberServiceMybatis;

public class MemberRemoveAjax implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		MemberService service = new MemberServiceMybatis();
		System.out.println("remove 될 ID 명 입니다 : " + request.getParameter("id"));
		boolean result = service.removeMember(request.getParameter("id"));
		String json = "";
		if(result) { // {"retCode": "Success"}
			json = "{\"retCode\": \"Success\"}";
		}else { // {"retCode": "Fail"}
			json = "{\"retCode\": \"Fail\"}";
		}
		
		
		return json + ".ajax";
	}

}
