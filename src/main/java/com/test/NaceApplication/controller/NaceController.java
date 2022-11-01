package com.test.NaceApplication.controller;

import com.test.NaceApplication.model.Nace;
import com.test.NaceApplication.service.NaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NaceController {
    @Autowired
    private NaceService naceService;

    @GetMapping("/getNaceDetails/{order}")
    public ResponseEntity<Nace> getNaceDetails(@PathVariable("order") Integer order) {
        Optional<Nace> tutorialData = naceService.getNaceByOrder(order);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/putNaceDetails")
    public ResponseEntity<Nace> putNaceDetails(@RequestBody Nace nace) {
        try {
            Nace createdNace = naceService
                    .putNaceDetails(nace);
            return new ResponseEntity<>(createdNace, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
