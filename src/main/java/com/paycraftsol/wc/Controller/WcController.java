package com.paycraftsol.wc.Controller;

import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paycraftsol.wc.Model.CountResult;
import com.paycraftsol.wc.Service.WcService;

@RestController
@RequestMapping("/count")
public class WcController{


    private WcService wcService;

    public WcController(WcService wcService){
        this.wcService=wcService;
    }
    @RequestMapping(consumes = "Multipart/form-data")
    public ResponseEntity<CountResult> count(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());

            CountResult result= wcService.countText(inputStreamReader);
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}