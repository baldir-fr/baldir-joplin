package fr.baldir.joplin;

import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import fr.baldir.joplin.ports.in.MarkdownParserPort;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class FlexmarkMarkdownParserAdapter implements MarkdownParserPort {
    @Override
    public String parse(String markdownContent) {
        final var parser = Parser.builder().build();
        var document = parser.parse(markdownContent);
        var renderer = HtmlRenderer.builder().build();

        return renderer.render(document);
    }

    @Override
    public FrontmatterMetadata parseFrontmatter(String markdownContent) {
        var frontmatterExtension = YamlFrontMatterExtension.create();

        final var builder = Parser.builder();
        frontmatterExtension.extend(builder);

        final var parser = builder.build();
        var document = parser.parse(markdownContent);

        final var frontmatterMetadataBuilder = FrontmatterMetadata.builder();
        var visitor = new AbstractYamlFrontMatterVisitor() {

        };
        visitor.visit(document);
        var visitedData = visitor.getData();
        if (visitedData.containsKey("title")) {
            final var title = visitedData.get("title").get(0);
            frontmatterMetadataBuilder.title(title);
        }
        if (visitedData.containsKey("date")) {
            final var date = visitedData.get("date").get(0);
            frontmatterMetadataBuilder.date(date);
        }
        if (visitedData.containsKey("tags")) {
            final var tag = visitedData.get("tags").get(0);
            var strippedTag = StringUtils.unwrap(tag, "\"");
            frontmatterMetadataBuilder.tags(Set.of(new FrontmatterTag(strippedTag)));
        }
        return frontmatterMetadataBuilder.build();
    }
}
