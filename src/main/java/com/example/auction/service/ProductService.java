package com.example.auction.service;

import com.example.auction.config.PrincipalDetails;
import com.example.auction.dto.CartDto;
import com.example.auction.dto.JoinDto;
import com.example.auction.dto.ProductDto;
import com.example.auction.entity.Cart;
import com.example.auction.entity.Product;
import com.example.auction.entity.Users;
import com.example.auction.repository.CartRepository;
import com.example.auction.repository.ProductRepository;
import com.example.auction.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public PrincipalDetails principalDetails;
    @Autowired
    private final CartRepository cartRepository;
    private UsersRepository usersRepository;


    public ProductService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }



//    public String saveImage(MultipartFile imageFile) throws IOException {
//        // 실제로는 파일 시스템에 저장하고 저장된 URL을 리턴해야 함
//        // 예시로 파일명을 리턴하는 방식 사용
//        return imageFile.getOriginalFilename();
//    }

    public Page<ProductDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 6; // 한페이지에 보여줄 글 개수
        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Product> postsPages = productRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "productId")));

        // 목록 : id, title, content, author
        Page<ProductDto> productDtos = postsPages.map(
                postPage -> new ProductDto(postPage));
        return productDtos;
    }
    public void saveProduct(Product product){
        productRepository.save(product);
    }


    private final String uploadDir = "C:/dev/auction/src/main/resources/static/newImages/"; // 파일을 저장할 디렉토리 경로를 지정

    public String saveImage(MultipartFile imageFile) throws IOException {
        // 업로드할 디렉토리가 존재하지 않으면 생성
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }

        // 파일명 생성 (중복을 피하기 위해 UUID 사용)
        String fileName = generateUniqueFileName(imageFile);

        // 파일 저장 경로 설정
        Path filePath = Paths.get(uploadDir + fileName);

        // 파일을 지정된 경로에 저장
        Files.copy(imageFile.getInputStream(), filePath);

        String file = "/newImages/"+fileName;

        // 저장된 파일의 경로를 반환 (예를 들어 웹 상에서 접근 가능한 URL을 반환할 수도 있음)
        return file;
    }

    // 파일명 생성 메서드
    private String generateUniqueFileName(MultipartFile multiPartFile) {
        return UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(multiPartFile.getOriginalFilename());
    }

    public ProductDto findOne(Long id){
        ProductDto productDto = productRepository.findById(id).map(x-> ProductDto.fromProduct(x)).orElse(null);
        return productDto;
    }
    public void updateProduct(ProductDto dto,double productPrice,int id) {
        if (dto.getProductPrice()<productPrice){
            Product product = Product.builder()
                    .ownerId(dto.getOwnerId())
                    .productId(dto.getProductId())
                    .imageUrl(dto.getImageUrl())
                    .createdTime(dto.getCreatedTime())
                    .productPrice(productPrice)
                    .finalBuyerId(id)
                    .formattedCreatedTime(dto.getFormattedCreatedTime())
                    .build();
            productRepository.save(product);
        }
    }


    public void saveCart(CartDto cartDto){
        Cart cart=cartDto.fromCartDto(cartDto);
        cartRepository.save(cart);
    }


    public CartDto findByProductId(int productId){
        Cart cart =cartRepository.findByProductId(productId);
        CartDto cartDto = CartDto.fromCart(cart);
        return cartDto;
    }
    public List<ProductDto> findByOwnerId(int ownerId) {
        List<Product> productList = productRepository.findByOwnerId(ownerId);
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = ProductDto.fromProduct(product);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public void updateCart(CartDto cartDto,double productPrice) {

                Cart cart = Cart.builder()
                        .cartId(cartDto.getCartId())
                        .userId(cartDto.getUserId())
                        .myPrice(productPrice)
                        .productId(cartDto.getProductId())
                        .done(cartDto.isDone())
                        .build();
            cartRepository.save(cart);
        }

    public List<Cart> getCartItemsByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public void saveOrUpdateProduct(ProductDto testDto,int testOwnerId,double productPrice) {
        int id = testDto.getProductId().intValue();
        Cart existingProduct = cartRepository.findByProductIdAndUserId(id,testOwnerId);

        if (existingProduct != null) {
            // productId에 해당하는 상품이 이미 존재하는 경우 업데이트
            existingProduct.setCartId(existingProduct.getCartId());
            existingProduct.setMyPrice(productPrice);
            cartRepository.save(existingProduct);
        } else {
            // productId에 해당하는 상품이 없는 경우 새로운 상품 추가
            Cart newProduct = new Cart();
            newProduct.setProductId(id);
            newProduct.setUserId(testOwnerId);
            newProduct.setDone(false);
            newProduct.setMyPrice(productPrice);
            cartRepository.save(newProduct);
        }
    }

    public void deleteCart(int id,int testOwnerId){
        Cart cart = cartRepository.findByProductIdAndUserId(id,testOwnerId);
        Long deleteId= cart.getCartId();
        cartRepository.deleteById(deleteId);
    }




}
