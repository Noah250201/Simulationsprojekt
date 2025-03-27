package org.dataprojectNHMT.services;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.InputDTO;
import org.dataprojectNHMT.dtos.out.DiagramDTO;
import org.dataprojectNHMT.dtos.out.PublisherStockAndViewsDTO;
import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.dataprojectNHMT.util.JsonMapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PublisherStockAndViewsService {

    private final DatabaseController db;

    public PublisherStockAndViewsService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputDTO input) {
        List<DiagramDTO> list = new ArrayList<>();
        List<String> values = input.getValues();

        String publisherName = values.get(0);
        PublisherEntity publisher = db.getPublisherByName(publisherName);

        LocalDate dateIndex = LocalDate.parse(values.get(1));

        LocalDate endDate = LocalDate.parse(values.get(2));
        long daysDifference = dateIndex.until(endDate, ChronoUnit.DAYS);

        while (daysDifference >= 0) {
            StockEntity stock = db.getStockByDateAndPublisher(dateIndex, publisher);
            AnalyticsEntity analytics = db.getAnalyticsByDateAndPublisher(dateIndex, publisher);

            PublisherStockAndViewsDTO dto = new PublisherStockAndViewsDTO();
            dto.setDate(dateIndex);
            dto.setStockprice(stock.getPrice());
            dto.setGoogleViewCount(analytics.getSearches());
            list.add(dto);

            dateIndex = dateIndex.plusDays(1);
            daysDifference = dateIndex.until(endDate, ChronoUnit.DAYS);
        }

        return JsonMapper.mapDTOsToArray(list);
    }
}
