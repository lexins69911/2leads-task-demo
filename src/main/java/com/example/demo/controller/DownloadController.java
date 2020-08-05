package com.example.demo.controller;

import com.example.demo.db.dto.HistoryDto;
import com.example.demo.db.entity.HistoryEntity;
import com.example.demo.db.repository.HistoryRepository;
import com.example.demo.util.converter.UserEntityToUserDtoConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("history/download")
@RestController
public class DownloadController {

    @Autowired
    HistoryRepository historyRepository;


    @GetMapping("{id}")
    public ResponseEntity<byte[]> getConcreteHistoryJsonFile(@PathVariable long id) throws JsonProcessingException {
        HistoryEntity byId = historyRepository.findById(id);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(byId);
        byte[] isr = json.getBytes();

        String fileName = String.format("history_%d.json", id);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<byte[]> getHistoryJsonFile() throws JsonProcessingException {
        List<HistoryDto> historyDtos = new ArrayList<>();

        Collection<HistoryEntity> allHistory =
                StreamSupport.stream(historyRepository.findAll().spliterator(),false)
                        .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();

        for (HistoryEntity historyEntity: allHistory) {
            historyDtos.add(converteToDto(historyEntity));
        }

        String json = objectMapper.writeValueAsString(historyDtos);
        byte[] isr = json.getBytes();

        String fileName = String.format("history.json");
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
    }

    private HistoryDto converteToDto(HistoryEntity historyEntity) {
        HistoryDto historyDto = new HistoryDto();
        UserEntityToUserDtoConverter converter = new UserEntityToUserDtoConverter(historyEntity.getUser());
        if (historyEntity!=null) {
            historyDto.setActionDate(historyEntity.getActionDate());
            historyDto.setId(historyEntity.getId());
            historyDto.setLogin(historyEntity.isLogin());
            historyDto.setUserIp(historyEntity.getUserIp());

            historyDto.setUser(converter.convert());

        }
        return historyDto;
    }
}
