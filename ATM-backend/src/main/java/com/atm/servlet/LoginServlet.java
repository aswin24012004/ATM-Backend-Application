package com.atm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.atm.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username"); 
		String pin = req.getParameter("pin");
		
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND pin=?"); 
			ps.setString(1, username); 
			ps.setString(2, pin); 
			
			ResultSet rs = ps.executeQuery(); 
			res.setContentType("application/json"); 
			PrintWriter out = res.getWriter();
			
			if (rs.next()) { 
				out.println("{\"status\":\"success\",\"message\":\"Login successful\"}"); 
				} 
			else { 
				out.println("{\"status\":\"error\",\"message\":\"Invalid credentials\"}"); 
			}
			
		}catch (Exception e) { 
			e.printStackTrace();
			}
	}

}
