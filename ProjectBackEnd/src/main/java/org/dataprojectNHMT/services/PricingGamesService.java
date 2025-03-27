package org.dataprojectNHMT.services;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.InputDTO;
import org.dataprojectNHMT.dtos.out.DiagramDTO;
import org.dataprojectNHMT.dtos.out.PricingGamesDTO;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.util.JsonMapper;

import java.util.ArrayList;
import java.util.List;

public class PricingGamesService {

    private final DatabaseController db;

    public PricingGamesService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputDTO input) {
        List<DiagramDTO> list = new ArrayList<>();
        List<String> values = input.getValues();

        values = values.stream()
                .filter(value -> !value.isEmpty()).toList();
        values = values.subList(1, values.size());

        values.forEach(
                game ->{
                    GameEntity entity = db.getGameByName(game);
                    PricingGamesDTO dto = new PricingGamesDTO();
                    dto.setGame(entity.getGameName());
                    dto.setCurrentPrice(entity.getCurrentPrice());
                    dto.setInitialPrice(entity.getInitialPrice());
                    list.add(dto);
                });

        return JsonMapper.mapDTOsToArray(list);
    }
}
