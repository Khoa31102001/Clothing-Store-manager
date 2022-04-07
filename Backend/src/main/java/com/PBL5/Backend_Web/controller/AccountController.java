package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.*;
import com.PBL5.Backend_Web.service.AccountServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping(path = "")
    public List<Account> GetAllAccount() {
        return accountService.GetAllAccount();
    }


    @GetMapping(path = "/ListAccountByName")
    public ResponseEntity<ResponseObject> GetAllAccountByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS,
                "List Account", accountService.GetAllAccountByName(name)));
    }


    @PostMapping(path = "/InsertAccount")
    public ResponseEntity<ResponseObject> InsertAccount(@RequestParam("password") String password, @RequestParam("idRole") String IdRole) {
        boolean valid = accountService.InsertAccount(password, IdRole);
        if (valid) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Insert Role successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(ResponseObject.FAIRLURE, "Insert Role dont successfully", ""));
    }

    @PostMapping(path = "/ChangePasswordAccount")
    public ResponseEntity<ResponseObject> ChangePassword(@NotNull @RequestBody String o) {
        JSONObject jsonObject = new JSONObject(o);
        if (jsonObject.getString("newPass").equals("") || jsonObject.getString("oldPass").equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(ResponseObject.FAIRLURE, "Please enter in full", ""));
        }
        String accountid = jsonObject.getString("accountId");
        String newPass = jsonObject.getString("newPass");
        String oldPass = jsonObject.getString("oldPass");
        ResultResponse response = accountService.ChangePassword(oldPass,newPass,accountid);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(),""));
    }

    @DeleteMapping(path = "/DeleteAccount/{id}")
    public ResponseEntity<ResponseObject> deleteAccount(@PathVariable String id) {

        ResultResponse response = accountService.deleteAccount(id);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }

    @GetMapping(path = "/GetUserByAccount")
    public User getUser(@RequestParam("IdAccount") String IdAccount){
        return accountService.getUserByAccount(IdAccount);
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Account account = accountService.GetAccountByName(username);
                String access_token = JWT.create().withSubject(account.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).
                        withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", Arrays.asList(account.getRole()).stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refreshh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> errors = new HashMap<>();
                errors.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                try {
                    new ObjectMapper().writeValue(response.getOutputStream(), errors);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new RuntimeException("refreshToken is missing");
        }
    }
}
