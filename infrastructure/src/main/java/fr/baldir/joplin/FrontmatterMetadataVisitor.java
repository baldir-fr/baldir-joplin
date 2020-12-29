package fr.baldir.joplin;

import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FrontmatterMetadataVisitor extends AbstractYamlFrontMatterVisitor {

    public FrontmatterMetadata toFrontMatterMetadata(){
        final var frontmatterMetadataBuilder = FrontmatterMetadata.builder();
        var visitedData = this.getData();
        if (visitedData.containsKey("title")) {
            final var title = visitedData.get("title").get(0);
            frontmatterMetadataBuilder.title(title);
        }
        if (visitedData.containsKey("date")) {
            final var date = visitedData.get("date").get(0);
            frontmatterMetadataBuilder.date(date);
        }
        if (visitedData.containsKey("tags")) {
            final var tagsRawValue = visitedData.get("tags").get(0);
            frontmatterMetadataBuilder.tags(mapToFrontmatterTags(tagsRawValue));
        }
        return frontmatterMetadataBuilder.build();
    }


    @NotNull
    private Set<FrontmatterTag> mapToFrontmatterTags(String tagsRawValue) {
        final var tagsWithoutBrackets = StringUtils.strip(tagsRawValue, "[]");
        return Arrays.stream(StringUtils.split(tagsWithoutBrackets, ","))
                .map(StringUtils::trim)
                .map(s -> StringUtils.unwrap(s, "\""))
                .map(FrontmatterTag::new)
                .collect(Collectors.toSet());
    }
}
