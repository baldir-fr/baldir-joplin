package fr.baldir.joplin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FrontmatterMetadata {
    private String title;
}
