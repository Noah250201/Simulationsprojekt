package org.dataprojectNHMT.services;

import org.dataprojectNHMT.Main;
import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.in.InputOneDTO;
import org.dataprojectNHMT.dtos.out.DiagramDTO;
import org.dataprojectNHMT.dtos.out.DiagramOneDTO;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.dataprojectNHMT.util.JsonMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiagramOneService {

    private final DatabaseController db;

    public DiagramOneService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputOneDTO input) {
        List<DiagramDTO> dtoList = new ArrayList<>();
        List<GameEntity> gameList = input.getGames().stream().map(db::getGameByName).toList();
        PublisherEntity publisher = db.getPublisherByName(input.getPublisher());
        LocalDate date = Main.LAST_DATE_ON_RECORD;

        for (GameEntity game : gameList) {
            DiagramOneDTO dto = new DiagramOneDTO();
            StockEntity stock = db.getStockByDateAndPublisher(date,publisher);
            dto.setGame(game.getGameName());
            dto.setAveragePlayers(game.getAveragedPlayersLastTwoWeeks());
            dto.setDate(date);
            dto.setStockprice(stock.getPrice());
            dtoList.add(dto);

            date = switch (input.getInterval()) {
                case "wöchentlich" -> date.minusWeeks(1);
                case "monatlich" -> date.minusMonths(1);
                default -> date.minusDays(1);
            };
        }

        return JsonMapper.mapDTOsToArray(dtoList);
    }
}
