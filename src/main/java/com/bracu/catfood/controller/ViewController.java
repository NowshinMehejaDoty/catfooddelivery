package com.bracu.catfood.controller;

import com.bracu.catfood.entity.*;
import com.bracu.catfood.repository.FoodRepository;
import com.bracu.catfood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    UserService userService;

    @Autowired
    FoodService foodService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    PlacementService placementService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup(Model model) {
        SignupUser user = new SignupUser();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(SignupUser user, RedirectAttributes redirAttrs) {
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            redirAttrs.addFlashAttribute("error", "Password does not match!!");
            return "redirect:/signup";
        }

        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setType(1);

        User newUser = userService.addUser(u);

        redirAttrs.addFlashAttribute("success", "Please login now.");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String getLogin(User user) {
        List<User> users = userService.getUserByUsername(user.getUsername());
        if(users != null) {
            String password = users.get(0).getPassword();
            if(password.equals(user.getPassword())) {
                if(users.get(0).getType() == 0) {
                    return "redirect:/seller-dashboard";
                }
                else {
                    return "redirect:/customer-dashboard";
                }
            }
        }

        return "error";
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model) {
        AuthUser au = userService.getAuthUser();
        if(au.getUserType() == 0)
            return "redirect:/seller-dashboard";
        else
            return "redirect:/customer-dashboard";
    }

    @GetMapping("/seller-dashboard")
    public String getSellerDashBoard(Model model) {
        AuthUser au = userService.getAuthUser();
        model.addAttribute("username", au.getUsername().substring(0, 1).toUpperCase() + au.getUsername().substring(1));
        return "seller_dashboard";
    }

    @GetMapping("/customer-dashboard")
    public String getCustomerDashBoard(Model model) {
        AuthUser au = userService.getAuthUser();
        model.addAttribute("username", au.getUsername().substring(0, 1).toUpperCase() + au.getUsername().substring(1));
        return "seller_dashboard";
    }

    @GetMapping("/foods")
    public String showFoods(Model model) {
        AuthUser au = userService.getAuthUser();
        List<Food> foods = foodService.getAllFoods();
        model.addAttribute("au", au);
        model.addAttribute("foods", foods);
        return "foods";
    }


//    Food Category Views
    @GetMapping("/add-food")
    public String addFood(Model model, RedirectAttributes redirAttrs) {
        Food food = new Food();
        model.addAttribute("food", food);

        AuthUser au = userService.getAuthUser();
        model.addAttribute("au", au);
        if(au.getUserType() != 0) {
            redirAttrs.addFlashAttribute("error", "No Access!!");
            return "redirect:/customer-dashboard";
        }

        return "food";
    }

    @PostMapping("/add-food")
    public String addFood(Food food, RedirectAttributes redirAttrs) {
        Food f = foodService.addFood(food);
        redirAttrs.addFlashAttribute("success", "Data saved successfully.");
        return "redirect:/foods";
    }

    @GetMapping("/food/edit/{id}")
    public String editFood(@PathVariable("id") Integer id, Model model, RedirectAttributes redirAttrs) {
        Food food = foodService.getFood(id);
        model.addAttribute("food", food);

        AuthUser au = userService.getAuthUser();
        model.addAttribute("au", au);
        if(au.getUserType() != 0) {
            redirAttrs.addFlashAttribute("error", "No Access!!");
            return "redirect:/customer-dashboard";
        }

        return "food";
    }

    // product views
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Integer productId, Model model, RedirectAttributes redirAttrs) {
        Product product = productService.getProductById(productId);

        model.addAttribute("product", product);

        AuthUser au = userService.getAuthUser();
        model.addAttribute("au", au);
        if(au.getUserType() != 0) {
            redirAttrs.addFlashAttribute("error", "No Access!!");
            return "redirect:/customer-dashboard";
        }

        return "add_product";
    }
    @PostMapping("/add-product")
    public String addProduct(Product product, RedirectAttributes redirAttrs) {
        Food food = foodService.getFood(product.getFood().getId());
        product.setFood(food);
        Product p = productService.addProduct(product);
        redirAttrs.addFlashAttribute("success", "Data saved successfully.");
        return "redirect:/foods";
    }

    @GetMapping("/add-product/{id}")
    public String addProduct(@PathVariable("id") Integer foodId, Model model, RedirectAttributes redirAttrs) {
        Product product = new Product();
        Food food = foodService.getFood(foodId);
        product.setFood(food);
        model.addAttribute("product", product);

        AuthUser au = userService.getAuthUser();
        model.addAttribute("au", au);
        if(au.getUserType() != 0) {
            redirAttrs.addFlashAttribute("error", "No Access!!");
            return "redirect:/customer-dashboard";
        }

        return "add_product";
    }


    @GetMapping("/products/{id}")
    public String showProducts(@PathVariable("id") Integer id, Model model) {
        Food food = new Food();
        List<Product> products =  productService.getProductsByFood(id);
        AuthUser au = userService.getAuthUser();
        model.addAttribute("au", au);
        model.addAttribute("products", products);
        model.addAttribute("foodId", id);

        return "product";
    }

    @GetMapping("/cart/add/{id}/{foodId}")
    public String addProductToCart(@PathVariable("id") Integer id, @PathVariable("foodId") Integer foodId, Model model, RedirectAttributes redirAttrs) {
        AuthUser user = userService.getAuthUser();
        cartService.addProductToCart(id, user);
        redirAttrs.addFlashAttribute("success", "Added to cart successfully.");
        return "redirect:/products/" + foodId;
    }


    // cart
    @GetMapping("/cart")
    public String showCart(Model model) {
        AuthUser user = userService.getAuthUser();
        List<Cart> cart = cartService.getCartByUser(user);
        List<CartDetail> cartDetail = null;
        if(cart.size() > 0)
             cartDetail = cartService.getCartDetails(cart.get(0).getId());

        Double totalCost = 0.0;
        int totalItem = 0;
        if(cartDetail != null) {
            for (CartDetail cd : cartDetail) {
                totalCost += cd.getProduct().getPrice();
                totalItem++;
            }
        }


        model.addAttribute("cart", cart.size() > 0 ?cart.get(0) : null);
        model.addAttribute("cartDetail", cartDetail);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalItem", totalItem);

        return "cart";
    }

    // payment
    @GetMapping("/payment/{id}")
    public String payment(@PathVariable("id") Integer cartId, Model model) {
        Cart cart = cartService.getCartById(cartId);
        List<CartDetail> cartDetail = null;
        if(cart != null)
            cartDetail = cartService.getCartDetails(cartId);
        Double totalCost = 0.0;
        int totalItem = 0;
        for(CartDetail cd: cartDetail) {
            totalCost += cd.getProduct().getPrice();
            totalItem++;
        }

        Placement placement = new Placement();
        placement.setCartId(cartId);

        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("placement", placement);

        return "payment";
    }

    @PostMapping("/payment")
    public String payment(Placement placement, RedirectAttributes redirAttrs) {
        List<CartDetail> cartDetails = cartService.getCartDetails(placement.getCartId());
        Double totalCost = 0.0;
        int totalItem = 0;
        for(CartDetail cd: cartDetails) {
            totalCost += cd.getProduct().getPrice();
            totalItem++;
        }
        placement.setTotalPrice(totalCost);
        AuthUser u = userService.getAuthUser();
        User user = userService.getUser(u.userId);
        placement.setUser(user);
        Placement p = placementService.addPlacement(placement);

        Cart cart = cartService.getCartById(placement.getCartId());
        cart.setIsPlaced(1);
        cartService.updateCart(cart);

        for(CartDetail cd: cartDetails) {
            PlacementDetail pd = new PlacementDetail();
            pd.setFood(cd.getFood());
            pd.setProduct(cd.getProduct());
            pd.setPlacement(p);
            pd.setPrice(cd.getProduct().getPrice());
            placementService.addPlacementDetail(pd);
        }

        redirAttrs.addFlashAttribute("success", "Order placed successfully.");
        return "redirect:/seller-dashboard";
    }


    // order
    @GetMapping("/placements")
    public String placements(Model model, RedirectAttributes redirAttrs) {
        AuthUser au = userService.getAuthUser();
        List<Placement> placements = null;
        if(au.getUserType() == 0) {
            placements = placementService.getAllPlacements();
        }
        else {
            placements = placementService.getPlacementByUser(au.getUserId());
        }

        model.addAttribute("placements", placements);

        return "placements";
    }

    @GetMapping("/placement/view/{id}")
    public String placement(@PathVariable("id") Integer placementId, Model model) {
        Placement placement = placementService.getPlacement(placementId);
        List<PlacementDetail> pds = placementService.getPlacementDetailsByPlacement(placementId);

        Double totalCost = 0.0;
        int totalItem = 0;
        for(PlacementDetail pd: pds) {
            totalCost += pd.getProduct().getPrice();
            totalItem++;
        }


        model.addAttribute("placements", placement);
        model.addAttribute("pds", pds);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalItem", totalItem);

        return "placement";
    }

}