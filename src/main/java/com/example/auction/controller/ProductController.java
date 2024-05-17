package com.example.auction.controller;

import com.example.auction.config.PrincipalDetails;
import com.example.auction.dto.CartDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.example.auction.dto.JoinDto;
import com.example.auction.dto.ProductDto;
import com.example.auction.entity.Cart;
import com.example.auction.entity.Product;
import com.example.auction.repository.ProductRepository;
import com.example.auction.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }





    public static int testOwnerId=1;

    @GetMapping("/register")
    public String registerProduct(){
        return "/articles/register";
    }
    @PostMapping("/uploadProduct")
    public String uploadProduct(@RequestParam("name") String name,
                                @RequestParam("price") double price,
                                @RequestParam("imageFile") MultipartFile imageFile,
                                RedirectAttributes redirectAttributes) throws IOException {
        // 이미지 파일을 서버에 업로드
        String imageUrl = productService.saveImage(imageFile);
        log.info(imageUrl);
        // 제품 정보를 데이터베이스에 저장
        Product product = new Product();
        product.setProductName(name);
        product.setOwnerId(testOwnerId);
        product.setProductPrice(price);
        product.setImageUrl(imageUrl);
        product.setCreatedTime(LocalDateTime.now());
        product.setFormattedCreatedTime(product.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        product.setFinalBuyerId(testOwnerId);
        productService.saveProduct(product);

        redirectAttributes.addFlashAttribute("message", "Product uploaded successfully!");
        return "redirect:/register";
    }

    @GetMapping("/main")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<ProductDto> postsPages = productService.paging(pageable);
        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());


        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "articles/main";
    }

    ProductDto testDto=new ProductDto();
    @GetMapping("/show")
    public String showArticle(@RequestParam("showId")Long id, Model model){
        ProductDto dto=new ProductDto();
        dto=productService.findOne(id);
        testDto=dto;
        dto.setFormattedCreatedTime(dto.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("dto",dto);
        return "/articles/showProduct";
    }

    @GetMapping("/submitProduct")
    public String submit(@RequestParam("productPrice") double productPrice){
        productService.updateProduct(testDto,productPrice,testOwnerId);
        log.info(String.valueOf(testOwnerId));
        productService.saveOrUpdateProduct(testDto,testOwnerId,productPrice);
        return "redirect:/main";
    }

    @GetMapping("/myProduct")
    public String cartPlace(Model model){
        List<Cart> cartItems = productService.getCartItemsByUserId(testOwnerId);
        List<JoinDto> joinDtoList = new ArrayList<>();
        for (Cart cart1 : cartItems) {
            ProductDto productDto=productService.findOne((long) cart1.getProductId());
            JoinDto joinDto = new JoinDto();
            joinDto.setProductId(cart1.getProductId());
            joinDto.setProductName(productDto.getProductName());
            joinDto.setImageUrl(productDto.getImageUrl());
            joinDto.setMyPrice(cart1.getMyPrice());
            joinDto.setProductPrice(productDto.getProductPrice());
            joinDto.setCreatedTime(productDto.getCreatedTime());
            joinDto.setFormattedCreatedTime(joinDto.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            joinDtoList.add(joinDto);
        }
//        List<ProductDto> productDtoList = productService.findByOwnerId(testOwnerId);
//        model.addAttribute("myList", productDtoList);
        model.addAttribute("List", joinDtoList);
        return "/articles/auctionCart";
    }

    @GetMapping("/deleteCartItem")
    public String deleteItem(@RequestParam("productId")int id){
        productService.deleteCart(id,testOwnerId);
        return "redirect:/myProduct";

    }
    @GetMapping("/checkout")
    public String cashItem(@RequestParam("productId")int id){
        return "/articles/cash";
    }

    @GetMapping("/showMyProduct")
    public String showMyProduct(Model model){
        List<ProductDto> productDtoList = productService.findByOwnerId(testOwnerId);
        model.addAttribute("myList", productDtoList);
        return "/articles/auctionCart2";
    }
}

