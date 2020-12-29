import com.vladsch.flexmark.html.HtmlRenderer;
import fr.baldir.joplin.ports.in.MarkdownParserPort;

import javax.swing.text.html.HTMLDocument;

public class FlexmarkMarkdownParserAdapter implements MarkdownParserPort {
    @Override
    public String parse(String markdownContent) {
        var document = com.vladsch.flexmark.parser.Parser.builder().build().parse(markdownContent);
        var renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
