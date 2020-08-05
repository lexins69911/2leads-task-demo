package com.example.demo.controller;


import com.example.demo.db.dto.HistoryDto;
import com.example.demo.db.entity.HistoryEntity;
import com.example.demo.db.repository.HistoryRepository;
import com.example.demo.util.converter.UserEntityToUserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("history/info")
public class HistoryInfoController {

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("{id}")
    public HistoryDto getConcreteHistory(@PathVariable long id) {
        HistoryEntity historyEntity = historyRepository.findById(id);
        if (historyEntity!=null) {
            return converteToDto(historyEntity);
        }
        return new HistoryDto();

    }

    @GetMapping()
    public List<HistoryDto> getAllHistory() {

        List<HistoryDto> allHistory = new ArrayList<>();

        Collection<HistoryEntity> allFtomDB =
                StreamSupport.stream(historyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (HistoryEntity historyEntity: allFtomDB) {
            allHistory.add(converteToDto(historyEntity));
        }
        return allHistory;
    }

    private HistoryDto converteToDto(HistoryEntity historyEntity) {
        HistoryDto historyDto = new HistoryDto();
        UserEntityToUserDtoConverter converter =
                new UserEntityToUserDtoConverter(historyEntity.getUser());
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
