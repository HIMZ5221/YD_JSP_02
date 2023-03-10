package co.prod.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.prod.common.Control;

public class ProductListControl implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		//db 결과 -> attribute("list")
		return "product/productList.tiles";
	}

}
