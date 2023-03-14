package co.prod.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prod.vo.ProductVO;

import co.prod.controller.MemberListAjax;
import co.prod.controller.MemberListControl;
import co.prod.controller.MemberRemoveAjax;
import co.prod.controller.MembersControl;
import co.prod.controller.ProductInfoControl;
import co.prod.controller.ProductListControl;
import co.prod.service.ProductService;
import co.prod.service.ProductServiceImpl;

public class FrontController extends HttpServlet{
	
	private Map<String, Control> map;
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init() throws ServletException {
		// url <-> control
		System.out.println("FrontController 의 init이 실행되었습니다.");
		map.put("/memberList.do", new MemberListControl());
		map.put("/members.do", new MembersControl());
		// Ajax 호출(SPA처리
		map.put("/memberListAjax.do", new MemberListAjax());
		map.put("/memberRemoveAjax.do", new MemberRemoveAjax());
		
		//상품 목록
		map.put("/productList.do", new ProductListControl());
		//상품한건정보.
		map.put("/productInfo.do", new ProductInfoControl());
		
		
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		System.out.println(uri + " 는 uri 의 데이터 입니다.");
		System.out.println(context + " 는 context 의 데이터 입니다.");
		
		String page = uri.substring(context.length());
		System.out.println(page + " 는 page 데이터 입니다(do page)");
		
		
		Control command = map.get(page);//command는 객체가 됨
		System.out.println(command + " 는 command 데이터 입니다");
		String viewPage = command.exec(req, resp);
		System.out.println(viewPage + " 는 viewPage 입니다.");
		
		if(viewPage.endsWith(".jsp")) {
			viewPage = "/WEB-INF/views/" + viewPage;
		//}else if(viewPage.endsWith(".tiles")) {
		} else if (viewPage.endsWith(".ajax")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().append(viewPage.substring(0, viewPage.length() -5));
			return;
		}
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.products();
		for(ProductVO i : list) {
			System.out.println(i);
		}
		req.setAttribute("list", list);
		System.out.println("리스트 키 만들었음.");
		
		//다른페이지로 넘어갈 수 있음.
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
		
		
		
		
	}
	
}
