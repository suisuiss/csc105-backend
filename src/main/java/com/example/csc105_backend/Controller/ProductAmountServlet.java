package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.UpdateProductAmount;
import com.google.gson.Gson;
import org.json.JSONObject;
import com.example.csc105_backend.Model.Error;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "ProductAmountServlet", value = "/product/amount")
public class ProductAmountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {

            String json = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                json += line;
            }

            JSONObject jsonObject = new JSONObject(json);
            Integer productID = (Integer) jsonObject.get("productID");
            Integer productAmount = (Integer) jsonObject.get("productAmount");

            UpdateProductAmount updateProductAmount = new UpdateProductAmount();
            boolean result = updateProductAmount.updateProductAmt(productID, productAmount);

            if (result) {
                json = new Gson().toJson(result);
            } else {
                Error error = new Error();
                 error.setErrorCode("400");
                error.setErrorMsg("Fail to Update Amount Product");
                json = new Gson().toJson(error);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

