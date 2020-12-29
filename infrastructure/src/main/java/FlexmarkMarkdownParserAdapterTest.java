import fr.baldir.joplin.ports.in.MarkdownParserPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FlexmarkMarkdownParserAdapterTest {
    @Test
    void empty_markdown_content_should_render_as_empty_string() {
        MarkdownParserPort parser =  new FlexmarkMarkdownParserAdapter();
        var renderedHtml = parser.parse("");
        assertThat(renderedHtml).isEqualTo("");
    }

    @Test
    void regular_markdown_content_should_render_as_paragraph() {
        MarkdownParserPort parser =  new FlexmarkMarkdownParserAdapter();
        var renderedHtml = parser.parse("test");
        assertThat(renderedHtml).isEqualToNormalizingNewlines("<p>test</p>\n");
    }

    @Test
    void bold_markdown_content_should_render_as_paragraph_with_bold_tag() {
        MarkdownParserPort parser =  new FlexmarkMarkdownParserAdapter();
        var renderedHtml = parser.parse("**test**");
        assertThat(renderedHtml).isEqualToNormalizingNewlines("<p><strong>test</strong></p>\n");
    }

}