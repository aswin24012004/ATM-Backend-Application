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
@WebServlet("/api/withdraw")
public class WithdrawServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        double amount = Double.parseDouble(req.getParameter("amount"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            res.setContentType("application/json");
            PrintWriter out = res.getWriter();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    PreparedStatement update = con.prepareStatement("UPDATE users SET balance=? WHERE username=?");
                    update.setDouble(1, balance - amount);
                    update.setString(2, username);
                    update.executeUpdate();
                    out.println("{\"status\":\"success\",\"newBalance\":" + (balance - amount) + "}");
                } else {
                    out.println("{\"status\":\"error\",\"message\":\"Insufficient funds\"}");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
