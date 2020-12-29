package fr.baldir.joplin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FrontmatterMetadata {
    private String title;
    /**
     * YYYY-MM-DD
     */
    private String date;
}
