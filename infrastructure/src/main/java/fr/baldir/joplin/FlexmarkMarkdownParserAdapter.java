package fr.baldir.joplin;

import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import fr.baldir.joplin.ports.in.MarkdownParserPort;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

        var visitor = new FrontmatterMetadataVisitor();
        visitor.visit(document);
        return visitor.toFrontMatterMetadata();
    }

}
