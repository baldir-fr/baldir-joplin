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
}
