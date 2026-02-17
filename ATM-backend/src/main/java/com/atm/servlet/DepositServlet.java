package com.atm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.atm.util.DBUtil;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/api/deposit")
public class DepositServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String amountStr = req.getParameter("amount");

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        if (amountStr == null || amountStr.isEmpty()) {
            out.println("{\"error\":\"Amount parameter is missing\"}");
            return;
        }

        try (Connection con = DBUtil.getConnection()) {
            double amount = Double.parseDouble(amountStr);

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET balance = balance + ? WHERE username=?");
            ps.setDouble(1, amount);
            ps.setString(2, username);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("{\"message\":\"Deposit successful\",\"amount\":" + amount + "}");
            } else {
                out.println("{\"error\":\"User not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"error\":\"Internal server error\"}");
        }
    }
}
