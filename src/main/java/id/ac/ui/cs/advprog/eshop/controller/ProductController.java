package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("isEditMode", false);
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldErrors().getFirst().getDefaultMessage());
        } else {
            productService.create(product);
        }
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product toBeEdited = productService.findById(id);
        model.addAttribute("isEditMode", true);
        model.addAttribute("product", toBeEdited);
        return "createProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@Valid Product product, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldErrors().getFirst().getDefaultMessage());
        } else {
            productService.edit(id, product);
        }
        return "redirect:../list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductPost(@PathVariable String id) {
        productService.delete(id);
        return "redirect:../list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "productList";
    }
}


