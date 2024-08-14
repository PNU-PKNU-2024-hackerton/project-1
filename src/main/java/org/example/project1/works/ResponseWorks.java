package org.example.project1.works;

import lombok.Builder;

import java.util.List;

@Builder
public record ResponseWorks(
        Long id,
        String description,
        int year,
        String imagePath
) {
    public Works toEntity(ResponseWorks responseWorks){
        return Works.builder()
                .id(responseWorks.id)
                .description(responseWorks.description)
                .workYear(responseWorks.year)
                .imagePath(responseWorks.imagePath)
                .build();
    }
}
