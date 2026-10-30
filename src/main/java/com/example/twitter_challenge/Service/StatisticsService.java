package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Statistics;
import com.example.twitter_challenge.Repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    public Optional<Statistics> getStatisticsById(Long id) {
        return statisticsRepository.findById(id);
    }

    public Statistics createStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    public Statistics updateStatistics(Long id, Statistics statistics) {
        if (statisticsRepository.existsById(id)) {
            statistics.setId(id);
            return statisticsRepository.save(statistics);
        }
        return null;
    }

    public void deleteStatistics(Long id) {
        statisticsRepository.deleteById(id);
    }
}
