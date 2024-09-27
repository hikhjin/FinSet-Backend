package com.kb.finance.service;

import com.kb.finance.dto.Community;
import com.kb.finance.dto.Stock;
import com.kb.finance.dto.StockChart;
import com.kb.finance.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor

public class StockService {
    private final StockMapper mapper;

    public List<Stock> getAllStocks(String sort) {

        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        List<Stock> stocks = mapper.selectAll(params);
        log.info("stocks: {}", stocks);
        if(stocks == null || stocks.isEmpty()) {
            log.info("No stocks found");
            throw new NoSuchElementException();
        }
        return stocks;
    }

    public Stock getStockById(long id) {
        return Optional.ofNullable(mapper.selectById(id))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<StockChart> getStockChart(long id) {
        List<StockChart> stockCharts = mapper.selectChart(id);
        if(stockCharts == null || stockCharts.isEmpty()) {
            log.info("No stocks found");
            throw new NoSuchElementException();
        }
        return stockCharts;
    }

    public List<Community> getCommunities(long id) {
        List<Community> communities = mapper.selectCommunity(id);
        if(communities == null || communities.isEmpty()) {
            log.info("No communities found");
            throw new NoSuchElementException();
        }

        return communities;
    }


}
