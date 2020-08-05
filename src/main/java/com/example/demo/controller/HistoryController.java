package com.example.demo.controller;

import com.example.demo.db.entity.HistoryEntity;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.HistoryRepository;
import com.example.demo.db.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("history")
@Controller
public class HistoryController {

    private UserRepository userRepository;
    private HistoryRepository historyRepository;

    public HistoryController(UserRepository userRepository, HistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }

    @GetMapping()
    public String getHistoryPage(Principal principal, Model model)
    {
//        List<UserEntity> all = userRepository.findAllByActive(true);
        Iterable<HistoryEntity> historyEntities = historyRepository.findAll();

        Collection<HistoryEntity> allHistory =
                StreamSupport.stream(historyEntities.spliterator(), false)
                .collect(Collectors.toList());


        model.addAttribute("allHistoryies", allHistory);
//        model.addAttribute("countOfAllUsers", all.size());
        model.addAttribute("principal", principal);
        return "history";
    }
}
