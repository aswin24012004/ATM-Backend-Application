package com.atm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.atm.util.DBUtil;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/api/balance")
public class BalanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            res.setContentType("application/json");
            PrintWriter out = res.getWriter();

            if (rs.next()) {
                out.println("{\"balance\":" + rs.getDouble("balance") + "}");
                System.out.println(rs.getDouble("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
