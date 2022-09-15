package com.example.carserviceapp.controller;

import com.example.carserviceapp.dto.request.MasterRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.mapper.MasterMapper;
import com.example.carserviceapp.model.Master;
import com.example.carserviceapp.service.MasterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;

    public MasterController(MasterService masterService,
                            MasterMapper masterMapper) {
        this.masterService = masterService;
        this.masterMapper = masterMapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for a new master creating. Available for authenticated users only. " +
            "Returns a newly-created master DTO")
    public MasterResponseDto createMaster(@RequestBody MasterRequestDto masterRequestDto) {
        return masterMapper.mapToDto(masterService.createMaster(masterMapper.mapToModel(masterRequestDto)));
    }

    @GetMapping
    @ApiOperation(value = "Returns a list of all masters DTOs")
    public List<Master> getAllMasters() {
        return masterService.getAllMasters();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns concrete master by master's id")
    public Master getMasterById(@PathVariable Long id) {
        return masterService.getMasterById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Endpoint for concrete master deleting by master's id. Available only for users with " +
            "ADMIN authority. Performs as soft-deleting by setting \"true\" to isDeleted field. " +
            "Returns a message about successful execution")
    public String deleteMasterById(@PathVariable Long id) {
        return masterService.deleteMasterById(id);
    }
}
