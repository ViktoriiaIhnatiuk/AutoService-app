package com.example.carserviceapp.controller;

import com.example.carserviceapp.model.Stuff;
import com.example.carserviceapp.service.StuffService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stuff")
public class StuffController {
    private final StuffService stuffService;

    public StuffController(StuffService stuffService) {
        this.stuffService = stuffService;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new stuff creating. Available for authenticated users only. " +
            "Returns a newly-created stuff DTO")
    public Stuff createStuff(@RequestBody Stuff stuff) {
        return stuffService.createStuff(stuff);
    }

    @GetMapping
    @ApiOperation(value = "Returns all ")
    public List<Stuff> getAllStuff() {
        return stuffService.getAllStuff();
    }

    @GetMapping("/{id}")
    public Stuff getStuffById(@PathVariable Long id) {
        return stuffService.getStuffById(id);
    }

    @DeleteMapping("/{id}")
        public String deleteStuffById(@PathVariable Long id) {
        return stuffService.deleteStuffById(id);
    }
}
