package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Statistics;
import com.example.twitter_challenge.Service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public List<Statistics> getAllStatistics() {
        return statisticsService.getAllStatistics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistics> getStatisticsById(@PathVariable Long id) {
        return statisticsService.getStatisticsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Statistics createStatistics(@RequestBody Statistics statistics) {
        return statisticsService.createStatistics(statistics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statistics> updateStatistics(@PathVariable Long id, @RequestBody Statistics statistics) {
        Statistics updatedStatistics = statisticsService.updateStatistics(id, statistics);
        if (updatedStatistics != null) {
            return ResponseEntity.ok(updatedStatistics);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistics(@PathVariable Long id) {
        statisticsService.deleteStatistics(id);
        return ResponseEntity.noContent().build();
    }
}
