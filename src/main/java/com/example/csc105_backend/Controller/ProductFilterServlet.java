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

@WebServlet(name = "ProductFilterServlet", value = "/product/filter")
public class ProductFilterServlet extends HttpServlet {

    private static final long serialVersionUID = 639509121834815365L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();){
            String json = "";
            String prodCat = request.getParameter("productCategory");
            GetProductByFilter getProductByFilter = new GetProductByFilter();

            List<Product> result = getProductByFilter.getProductByFilter(prodCat);
            ProductImageList productImageList = new ProductImageList();
            if (null != result && !result.isEmpty()) {
                for (Product prod : result) {
                    List<ProductImage> prodImg = productImageList.getProductImgList(prod.getProductId());
                    if (null != prodImg && !prodImg.isEmpty()) {
                        String[] urlSplit = request.getRequestURL().toString().split("product");
                        prod.setProductPics(urlSplit[0] + Constants.PATH_DISPLAY + prodImg.get(0).getProductPicUrl()
                                + Constants.PATH_DISPLAY_2 + prod.getProductId());
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
