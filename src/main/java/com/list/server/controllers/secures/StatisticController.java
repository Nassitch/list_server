package com.list.server.controllers.secures;

import com.list.server.models.responses.StatisticResponse;
import com.list.server.services.StatisticChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/stats")
@RequiredArgsConstructor
@Component("statisticController")
public class StatisticController {

    private final StatisticChartService service;

    @GetMapping("/invoice/{id}/{year}")
    public StatisticResponse readStaticsById(@PathVariable("id") Long id, @PathVariable("year") int year) {
        return this.service.getStatistics(id, year);
    }
}
