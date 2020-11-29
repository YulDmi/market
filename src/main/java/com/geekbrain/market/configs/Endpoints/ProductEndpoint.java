package com.geekbrain.market.configs.Endpoints;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.soap.GetAllProductSOAPResponse;
import com.geekbrain.market.soap.GetProductSOAPRequest;
import com.geekbrain.market.soap.GetProductSOAPResponse;
import com.geekbrain.market.soap.ProductSOAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/market/soap";

    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductSOAPRequest")
    @ResponsePayload
    public GetProductSOAPResponse getProduct(@RequestPayload GetProductSOAPRequest request) {
        GetProductSOAPResponse response = new GetProductSOAPResponse();
        Product product = productService.findById(request.getId()).orElseThrow(() -> new ResourceNotFoundException("flsjgdlgj"));

        response.setProductSOAP(productService.getProductSOAP(product));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductSOAPRequest")
    @ResponsePayload
    public GetAllProductSOAPResponse getAllProduct() {
        GetAllProductSOAPResponse gapr = new GetAllProductSOAPResponse();
        List<Product> list = productService.findAll();
        List<ProductSOAP> productSOAPList = list.stream().map(p -> productService.getProductSOAP(p)).collect(Collectors.toList());
        gapr.getProductsSOAP().addAll(productSOAPList);
        return gapr;
    }


}
