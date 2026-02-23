package com.scaler.ProductService.exceptionHandler;

import com.scaler.ProductService.dtos.ExceptionDto;
import com.scaler.ProductService.dtos.ProductNotFoundDto;
import com.scaler.ProductService.exception.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers
{

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Void> handleAirthemeticException(){
        ResponseEntity<Void> responseEntity =new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBound(){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("Nothing can be done");
        ResponseEntity<ExceptionDto> responseEntity =new ResponseEntity<>(exceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ProductNotFoundDto> handleProductNotFoundException(){
        ProductNotFoundDto dto=new ProductNotFoundDto();
        dto.setMessage("Product with the given id not found");
        return new ResponseEntity<ProductNotFoundDto>(dto, HttpStatus.NOT_FOUND);
    }

}
