package com.PBL5.Backend_Web.persistence;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResultResponse {
    private String result;
    private String dataResult;
    private HttpStatus status;
}
