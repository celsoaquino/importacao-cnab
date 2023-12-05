package com.celsoaquino.backend.web;

import com.celsoaquino.backend.service.CnabService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("cnab")
public class CnabController {

    private final CnabService cnabService;

    public CnabController(CnabService cnabService) {
        this.cnabService = cnabService;
    }

    @PostMapping("upload")
    @CrossOrigin(origins = {"http://localhost:9090", "https://frontend-pagnet-ef5r.onrender.com"})
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        cnabService.uploadCnabFile(file);
        return ResponseEntity.ok("Successfully uploaded!");
    }
}
