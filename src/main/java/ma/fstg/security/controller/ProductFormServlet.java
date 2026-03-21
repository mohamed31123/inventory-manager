package ma.fstg.security.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstg.security.dao.ProductDAO;
import ma.fstg.security.model.Product;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/product-form")
public class ProductFormServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("edit")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Optional<Product> product = productDAO.findById(id);
            product.ifPresent(p -> request.setAttribute("product", p));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }
}
