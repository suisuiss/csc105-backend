package com.example.csc105_backend.Controller;

import com.example.csc105_backend.Model.*;
import com.example.csc105_backend.Model.Error;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductHistoryServlet", value = "/history")
public class ProductHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();){
            String json = "";
            String userId = request.getParameter("userID");
            GetProductHistory getProductHistory = new GetProductHistory();
            ProductImageList productImageList = new ProductImageList();
            List<Product> result = getProductHistory.getProductHistory(Integer.valueOf(userId),"BUYER");

            if(null != result && !result.isEmpty()) {
                for(Product prod : result) {
                    List<ProductImage> prodImg = productImageList.getProductImgList(prod.getProductId());
                    if(null != prodImg && !prodImg.isEmpty()) {
                        String[] urlSplit = request.getRequestURL().toString().split("history");
                        prod.setProductPics(urlSplit[0]+Constants.PATH_DISPLAY+prodImg.get(0).getProductPicUrl()+Constants.PATH_DISPLAY_2+prod.getProductId());
                    }
                }

                json = new Gson().toJson(result);
                json = json.replaceAll("\\u003d", "=");
            }else {
                Error bean = new Error();
                bean.setErrorCode("400");
                bean.setErrorMsg("Not found Product");
                json = new Gson().toJson(bean);
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
