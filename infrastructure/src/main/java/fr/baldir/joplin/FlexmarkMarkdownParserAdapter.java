package fr.baldir.joplin;

import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import fr.baldir.joplin.ports.in.MarkdownParserPort;

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
        var visitor = new AbstractYamlFrontMatterVisitor(){

        };
        visitor.visit(document);
        var visitedData = visitor.getData();
        if(visitedData.containsKey("title")){
            frontmatterMetadataBuilder.title(visitedData.get("title").get(0));
        }
        return  frontmatterMetadataBuilder.build();
    }
}
