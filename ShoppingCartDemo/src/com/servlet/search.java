package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dao.ShoppingCartDao;
import com.dao.impl.ShoppingCartDaoImpl;
import com.entity.ShoppingCart;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("text");
		 response.setContentType("text/text");         
		 response.setCharacterEncoding("UTF-8");
		ShoppingCartDao dao=new ShoppingCartDaoImpl();
		List<ShoppingCart> shoppingCarts=new ArrayList<ShoppingCart>();
		if (text==null) {
			 shoppingCarts = dao.select();
		}else {
			 shoppingCarts = dao.select(text);
		}
		
		String json=JSON.toJSONString(shoppingCarts);
		response.getWriter().write(json);
		
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
