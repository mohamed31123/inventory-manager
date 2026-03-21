package ma.fstg.security.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstg.security.dao.ProductDAO;
import ma.fstg.security.model.Product;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/create-product")
public class ProductCreateServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Integer stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String sku = request.getParameter("sku");

        Product newProduct = new Product(name, description, price, stockQuantity, sku);
        productDAO.save(newProduct);

        response.sendRedirect("products");
    }
}
