package com.example.csc105_backend.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.csc105_backend.Model.*;

import com.example.csc105_backend.Model.Error;
import com.google.gson.Gson;

@WebServlet(name = "MenuProductServlet", value = "/product/menu")
public class MenuProductServlet extends HttpServlet {

    private static final long serialVersionUID = 4111455261740107787L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();){
            String json = "";

            GetProductMenu getProductMenu = new GetProductMenu();
            List<String> result = getProductMenu.getProductMenu();
            if(null != result && !result.isEmpty()) {
                json = new Gson().toJson(result);
            }else {
                Error error = new Error();
               error.setErrorCode("400");
                error.setErrorMsg("Not found Menu");
                json = new Gson().toJson(error);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
