package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.*;
import com.PBL5.Backend_Web.repository.ProductDetailsRepo;
import com.PBL5.Backend_Web.repository.ProductRepo;
import com.PBL5.Backend_Web.repository.SizeRepo;
import com.PBL5.Backend_Web.repository.StoreRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Autowired
    private ProductDetailsRepo detailsRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SizeRepo sizeRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<ProductDetail> getAllProductDetail() {
        return detailsRepo.findAll();
    }

    @Override
    public ResultResponse InsertProductDetails(String P_id, String S_id, String St_id, ProductDetail productDetail) {
        ResultResponse responseCloudinary = cloudinaryService.UploadImage(productDetail.getFile(), "product");

        if (responseCloudinary.getResult().equals(ResponseObject.FAIRLURE))
            return new ResultResponse(responseCloudinary.getResult(), responseCloudinary.getDataResult(), responseCloudinary.getStatus());
        String img = responseCloudinary.getDataResult();
        productDetail.setImage(img);
        ProductDetail.Id id = new ProductDetail.Id(P_id, S_id);
        Optional<ProductDetail> optionalId = detailsRepo.findById(id);
        if (optionalId.isPresent()) {
            return new ResultResponse(ResponseObject.FAIRLURE, "ProductDetails is now available", HttpStatus.NOT_IMPLEMENTED);
        }
        Product product = productRepo.getById(P_id);
        Size size = sizeRepo.getById(S_id);
        Store store = storeRepo.getById(St_id);
        productDetail.setId(id);
        productDetail.setProduct(product);
        productDetail.setSize(size);
        productDetail.setStore(store);
        detailsRepo.save(productDetail);
        return new ResultResponse(ResponseObject.SUCCESS, "Insert ProductDetails successfully", HttpStatus.OK);
    }

    @Override
    public ResultResponse UpdateProductDetails(String P_id, String S_id, String St_id, ProductDetail productDetail) {

        ProductDetail.Id id = new ProductDetail.Id(P_id, S_id);
        ProductDetail productDetail1 = detailsRepo.getById(id);

        boolean isvalid = false;
        if (productDetail1.getImage() != null) {
            isvalid = cloudinaryService.DeleteImage("product", productDetail1.getImage());
            System.out.println(isvalid);
        }
        ResultResponse responseCloudinary = cloudinaryService.UploadImage(productDetail.getFile(), "product");

        if (responseCloudinary.getResult().equals(ResponseObject.FAIRLURE))
            return new ResultResponse(responseCloudinary.getResult(), responseCloudinary.getDataResult(), responseCloudinary.getStatus());
        String img = responseCloudinary.getDataResult();
        productDetail.setImage(img);
        Store store = storeRepo.getById(St_id);
        Optional<ProductDetail> optionalId = detailsRepo.findById(id);

        if (optionalId.isPresent()) {
            detailsRepo.UpdateDetailsById(P_id, S_id, St_id, productDetail.getImage(), productDetail.getPrice(), productDetail.getAmount());
            return new ResultResponse(ResponseObject.SUCCESS, "Update ProductDetails successfully", HttpStatus.OK);


        }
        Product product = productRepo.getById(P_id);
        Size size = sizeRepo.getById(S_id);
        productDetail.setId(id);
        productDetail.setProduct(product);
        productDetail.setSize(size);
        productDetail.setStore(store);
        detailsRepo.save(productDetail);
        return new ResultResponse(ResponseObject.SUCCESS, "Insert ProductDetails successfully", HttpStatus.OK);
    }

    @Override
    public ResultResponse DeleteProductDetails(String P_id, String S_id) {
        ProductDetail.Id id = new ProductDetail.Id(P_id, S_id);
        boolean valid = detailsRepo.existsById(id);
        if (valid) {
            detailsRepo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS, "Delete ProductDetails successfully with id = " + id.toString(), HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE, "ProductDetails not Exists", HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public List<String> GetSameImageFromClient(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            DatagramSocket clientSocket = new DatagramSocket(5000);

            InetAddress IPAddress = InetAddress.getByName("localhost");
            while (true) {

                byte[] sendData = new byte[2048];
                byte[] receiveData = new byte[2048];

                sendData = file.getBytes();

                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

                clientSocket.send(sendPacket);

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);

                clientSocket.receive(receivePacket);
                String modified_Sentence = new String(receivePacket.getData());
                    break;
            }
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
