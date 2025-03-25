package org.dataprojectNHMT.services;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.in.InputTwoDTO;
import org.dataprojectNHMT.dtos.out.DiagramDTO;
import org.dataprojectNHMT.dtos.out.DiagramTwoDTO;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.util.JsonMapper;

import java.util.ArrayList;
import java.util.List;

public class DiagramTwoService {

    private final DatabaseController db;

    public DiagramTwoService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputTwoDTO input) {
        List<DiagramDTO> list = new ArrayList<>();
        input.getGames().forEach(
                game ->{
                    GameEntity entity = db.getGameByName(game);
                    DiagramTwoDTO dto = new DiagramTwoDTO();
                    dto.setGame(entity.getGameName());
                    dto.setCurrentPrice(entity.getCurrentPrice());
                    dto.setInitialPrice(entity.getInitialPrice());
                    list.add(dto);
                });

        return JsonMapper.mapDTOsToArray(list);
    }
}
