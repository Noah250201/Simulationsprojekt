package org.dataprojectNHMT.util;

import org.dataprojectNHMT.dtos.out.DiagramDTO;

import java.util.List;

public class JsonMapper {
    public static String mapDTOsToArray(List<DiagramDTO> dto) {
        int lastElementIndex = dto.size() - 1;
        StringBuilder builder = new StringBuilder("{[");

        for (int i = 0; i < lastElementIndex; i++) {
            builder.append(dto.get(i).toJson());
            builder.append(',');
        }
        builder.append(dto.get(lastElementIndex).toJson());

        builder.append("]}");
        return builder.toString();
    }
}
