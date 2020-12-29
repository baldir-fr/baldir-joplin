import fr.baldir.joplin.ports.in.MarkdownParserPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FlexmarkMarkdownParserAdapterTest {

    @Test
    void empty_markdown_content_should_render_as_empty_string() {
        assertMarkdownIsParsedAs(
                "",
                "");
    }


    @Test
    void regular_markdown_content_should_render_as_paragraph() {

        assertMarkdownIsParsedAs(
                "test",
                "<p>test</p>\n");
    }

    @Test
    void bold_markdown_content_should_render_as_paragraph_with_bold_tag() {

        assertMarkdownIsParsedAs(
                "**test**",
                "<p><strong>test</strong></p>\n");
    }

    @Test
    void emphase_markdown_content_should_render_as_paragraph_with_emphase_tag() {

        assertMarkdownIsParsedAs(
                "*test*",
                "<p><em>test</em></p>\n");
    }

    @Test
    void list_markdown_content_should_render_as_html_list() {

        assertMarkdownIsParsedAs(
                "- item 1\n" +
                        "- item 2",
                "<ul>\n" +
                        "<li>item 1</li>\n" +
                        "<li>item 2</li>\n" +
                        "</ul>\n");
    }

    private MarkdownParserPort parserUnderTest() {
        return new FlexmarkMarkdownParserAdapter();
    }


    private void assertMarkdownIsParsedAs(String markdownInput, String expectedHtmlOutput) {
        MarkdownParserPort parser = parserUnderTest();
        var renderedHtml = parser.parse(markdownInput);
        assertThat(renderedHtml).isEqualToNormalizingNewlines(expectedHtmlOutput);
    }
}