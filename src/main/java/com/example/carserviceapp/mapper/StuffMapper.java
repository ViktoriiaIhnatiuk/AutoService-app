package com.example.carserviceapp.mapper;

import com.example.carserviceapp.dto.request.StuffRequestDto;
import com.example.carserviceapp.dto.response.StuffResponseDto;
import com.example.carserviceapp.model.Stuff;
import org.springframework.stereotype.Component;

@Component
public class StuffMapper implements RequestMapper<StuffRequestDto, Stuff>,
        ResponseMapper<Stuff, StuffResponseDto> {
    @Override
    public Stuff mapToModel(StuffRequestDto stuffRequestDto) {
        Stuff stuff = new Stuff();
        stuff.setName(stuffRequestDto.getName());
        stuff.setPrice(stuffRequestDto.getPrice());
        return stuff;
    }

    @Override
    public StuffResponseDto mapToDto(Stuff stuff) {
        StuffResponseDto stuffResponseDto = new StuffResponseDto();
        stuffResponseDto.setId(stuff.getId());
        stuffResponseDto.setName(stuff.getName());
        stuffResponseDto.setPrice(stuff.getPrice());
        stuffResponseDto.setDeleted(stuff.isDeleted());
        return stuffResponseDto;
    }
}
