package fr.baldir.joplin;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class FrontmatterMetadata {
    private String title;
    /**
     * YYYY-MM-DD
     */
    private String date;
    private Set<FrontmatterTag> tags;
}
