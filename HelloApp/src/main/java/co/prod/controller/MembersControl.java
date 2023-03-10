package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;

public class MembersControl implements Control {

	
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "member/member.tiles";
	}

}
