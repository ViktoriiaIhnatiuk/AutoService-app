package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.MasterRequestDto;
import com.example.carserviceapp.dto.response.MasterResponseDto;
import com.example.carserviceapp.model.Master;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper implements RequestMapper<MasterRequestDto, Master>,
    ResponseMapper<Master, MasterResponseDto> {
    @Override
    public Master mapToModel(MasterRequestDto masterRequestDto) {
        Master master = new Master();
        master.setName(masterRequestDto.getName());
        return master;
    }

    @Override
    public MasterResponseDto mapToDto(Master master) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(master.getId());
        masterResponseDto.setName(master.getName());
        masterResponseDto.setFinishedOrders(master.getFinishedOrders());
        masterResponseDto.setDeleted(master.isDeleted());
        return masterResponseDto;
    }
}
