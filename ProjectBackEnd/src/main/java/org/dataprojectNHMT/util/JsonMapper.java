package org.dataprojectNHMT.util;

import org.dataprojectNHMT.dtos.out.DiagramDTO;

public class JsonMapper {
    public static String mapDTOsToArray(DiagramDTO[] dto) {
        int lastElementIndex = dto.length - 1;
        StringBuilder builder = new StringBuilder("{[");

        for (int i = 0; i < lastElementIndex; i++) {
            builder.append(dto[i].toJson());
            builder.append(',');
        }
        builder.append(dto[lastElementIndex].toJson());

        builder.append("]}");
        return builder.toString();
    }
}
