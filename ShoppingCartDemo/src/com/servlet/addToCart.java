package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ShoppingCartDao;
import com.dao.ShoppingDao;
import com.dao.impl.ShoppingCartDaoImpl;
import com.dao.impl.ShoppingDaoImpl;
import com.entity.Shopping;
import com.entity.ShoppingCart;



@WebServlet("/addToCart")
public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public addToCart() {
        super();
        // TODO Auto-generated constructor stub\
        HttpServletResponse httpServletResponse = null;
        try {
			httpServletResponse.sendRedirect("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String str=request.getParameter("pidNum");
			ShoppingCartDao dao=new ShoppingCartDaoImpl();
			ShoppingDao shoppingdao=new ShoppingDaoImpl();
			
			String[] split = str.split(",");
			for (String string : split) {
				Long id=Long.parseLong(string);
				
				
				List<Shopping> shoppinglist = shoppingdao.select(id);
				for (Shopping shopping : shoppinglist) {
					List<ShoppingCart> shoppingCartList = dao.select(shopping.getShoppingname());
					
						dao.save(shopping);
						
						
					
					
				}
			}
					
			
			
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
