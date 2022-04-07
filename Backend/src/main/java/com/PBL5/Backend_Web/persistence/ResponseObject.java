package com.PBL5.Backend_Web.persistence;


import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseObject {
    public static String SUCCESS = "OK";
    public static String FAIRLURE = "FAILED";
    private String status;
    private String message;
    private Object data;
}
