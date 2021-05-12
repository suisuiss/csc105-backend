package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.Error;
import com.example.csc105_backend.Model.UpdateBuyer;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "ProductTransServlet", value = "/product/transaction")
public class ProductTransServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
             BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))) {

            String json = "";
            String line = "";
            boolean result = false;
            while ((line = br.readLine()) != null) {
                json += line;
            }
            JSONArray jsonArray = new JSONArray(json);
            for (Object jsonObject : jsonArray) {
                if(jsonObject instanceof JSONObject)
                {
                    JSONObject jsonObj = (JSONObject)jsonObject;
                    Integer productID = (Integer) jsonObj.get("productId");
                    String buyerID = (String) jsonObj.get("buyerId");
                    BigDecimal productPrice = BigDecimal.ZERO;
                    if(jsonObj.get("productPrice") instanceof BigDecimal) {
                        productPrice = (BigDecimal) jsonObj.get("productPrice");
                    }else {
                        Integer price = (Integer) jsonObj.get("productPrice");
                        productPrice = BigDecimal.valueOf(price);
                    }


                    UpdateBuyer updateBuyer= new UpdateBuyer();
                    result = updateBuyer.updateProductBuyer(productID, Integer.valueOf(buyerID), productPrice);
                    if(!result) {
                        break;
                    }
                }
            }

            if (result) {
                json = new Gson().toJson(result);
            } else {
                Error bean = new Error();
                bean.setErrorCode("400");
                bean.setErrorMsg("Fail to Update Buyer Product");
                json = new Gson().toJson(bean);
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
