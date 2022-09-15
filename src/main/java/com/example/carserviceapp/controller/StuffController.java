package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.StuffRequestDto;
import com.example.carserviceapp.dto.response.StuffResponseDto;
import com.example.carserviceapp.mapper.StuffMapper;
import com.example.carserviceapp.service.StuffService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stuff")
public class StuffController {
    private final StuffService stuffService;
    private final StuffMapper stuffMapper;

    public StuffController(StuffService stuffService,
                           StuffMapper stuffMapper) {
        this.stuffService = stuffService;
        this.stuffMapper = stuffMapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new stuff creating. Available for authenticated users only. " +
            "Returns a newly-created stuff DTO")
    public StuffResponseDto createStuff(@RequestBody StuffRequestDto stuffRequestDto) {
        return stuffMapper.mapToDto(stuffService.createStuff(stuffMapper.mapToModel(stuffRequestDto)));
    }

    @GetMapping
    @ApiOperation(value = "Returns all stuff DTOs")
    public List<StuffResponseDto> getAllStuff() {
        return stuffService.getAllStuff().stream()
                .map(stuffMapper:: mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StuffResponseDto getStuffById(@PathVariable Long id) {
        return stuffMapper.mapToDto(stuffService.getStuffById(id));
    }

    @DeleteMapping("/{id}")
        public String deleteStuffById(@PathVariable Long id) {
        return stuffService.deleteStuffById(id);
    }
}
