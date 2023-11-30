package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.dto.PageRequestDTO;
import com.example.shop.dto.PageResponseDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.repository.OrderProductRepository;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    @Override
    public void productRegister(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
    }
    @Override
    public ProductDTO readOne(Long productId) {
        Optional<Product> result = productRepository.findById(productId);
        Product product = result.orElseThrow(() -> new RuntimeException("Product not found"));


        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return productDTO;
    }

    @Override
    public PageResponseDTO<ProductDTO> readAll(PageRequestDTO pageRequestDTO) {
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        Page<Product> result = productRepository.searchAll(keyword, pageable);

        List<ProductDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, ProductDTO.class))
                .collect(Collectors.toList());

        return  PageResponseDTO.<ProductDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<ProductDTO> readTypeAll(String pType, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable();
        Page<Product> result = productRepository.findTypeAll(pType, pageable);

        List<ProductDTO> dtoList = result.getContent().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<ProductDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public List<ProductDTO> readTop8(){
        List<Product> result = orderProductRepository.findTop8Products();
        List<ProductDTO> productDTO = result.stream()
                .map(Product -> modelMapper.map(Product, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTO;
    }

    @Override
    public PageResponseDTO<ProductDTO> readWithImageAll(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable();
        Page<Product> result = productRepository.findWithImages(pageable);

        List<ProductDTO> dtoList = result.getContent().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<ProductDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }
    @Override
    public ProductDTO readOnewithImage(long productId){
        Optional<Product> result = productRepository.findByIdWithImages(productId);
        Product product = result.orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return productDTO;

    }


}





