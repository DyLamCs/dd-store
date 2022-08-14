package com.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.CartModel;
import com.web.model.ProductModel;
import com.web.services.IProductShowAllService;

/**
 * Tổng bill đặt hàng
 * @author IVS
 *
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	String URL_GET_PRODUCT = "http://localhost:8081/api/product/";
	@Autowired
	IProductShowAllService iProductShowAllService;
	
	@GetMapping() 
	 public String checkout() { 
		 return "checkout/checkout"; 
	 }
	
	
	/**
	 * Thêm sản phẩm vào giỏ hàng
	 * @param proId
	 * @param session
	 * @return
	 */
	@GetMapping("/add/{proId}")
	public String addToCart(@PathVariable("proId") Integer proId, HttpSession session) {
		HashMap<Integer, CartModel> cartModel = (HashMap<Integer, CartModel>) session.getAttribute("myCartModel");
		if(cartModel == null) {
			cartModel = new HashMap<>();
		}
		ProductModel product = iProductShowAllService.ShowOneProduct(URL_GET_PRODUCT, proId).getData();
		if(product!= null) {
			if(cartModel.containsKey(proId)) {
				CartModel cart = cartModel.get(proId);
				cart.setProductmodel(product);
				cart.setQuantiti(cart.getQuantiti() + 1);
				cartModel.put(proId, cart);
			}else {
				CartModel cart = new CartModel();
				cart.setProductmodel(product);
				cart.setQuantiti(1);
				cartModel.put(proId, cart);
			}
		}
		session.setAttribute("myCartModel", cartModel);
		session.setAttribute("total", totalPrice(cartModel));
		session.setAttribute("number", cartModel.size());
		return "cart/cart";
		
	}

	/**
	 * Tổng giá trị của đơn hàng
	 * @param cartModel
	 * @return
	 */
	public double totalPrice(HashMap<Integer, CartModel> cartModel) {
        int count = 0;
        //duyệt qua các sản phẩm trong giỏ hàng
        for (Map.Entry<Integer, CartModel> list : cartModel.entrySet()) {
        	// cộng lại giá sản phẩm
            count += Integer.parseInt(list.getValue().getProductmodel().getProPrice()) * list.getValue().getQuantiti();
        }
        return count;
    }


	
}
