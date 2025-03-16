package org.dataprojectNHMT.services;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.in.InputThreeDTO;
import org.dataprojectNHMT.dtos.out.DiagramDTO;
import org.dataprojectNHMT.dtos.out.DiagramThreeDTO;
import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.dataprojectNHMT.util.JsonMapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DiagramThreeService {

    private final DatabaseController db;

    public DiagramThreeService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputThreeDTO input) {
        List<DiagramDTO> list = new ArrayList<>();
        PublisherEntity publisher = db.getPublisherByName(input.getPublisher());

        LocalDate currentDate = input.getStartDate();
        long daysDifference = currentDate.until(input.getEndDate(), ChronoUnit.DAYS);

        while (daysDifference >= 0) {
            StockEntity stock = db.getStockByDateAndPublisher(currentDate, publisher);
            AnalyticsEntity analytics = db.getAnalyticsByDateAndPublisher(currentDate, publisher);

            DiagramThreeDTO dto = new DiagramThreeDTO();
            dto.setDate(currentDate);
            dto.setStockprice(stock.getPrice());
            dto.setGoogleViewCount(analytics.getSearches());
            list.add(dto);

            currentDate = currentDate.plusDays(1);
            daysDifference = currentDate.until(input.getEndDate(), ChronoUnit.DAYS);
        }

        return JsonMapper.mapDTOsToArray(list);
    }
}
