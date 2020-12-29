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

    @Test
    void joplin_attachment_image_markdown_content_should_render_as_html_image() {

        assertMarkdownIsParsedAs(
                "![Screenshot_20200824-183242.png](:/f805dbfc145d4544bec760448298f08b)",
                "<p><img src=\":/f805dbfc145d4544bec760448298f08b\" alt=\"Screenshot_20200824-183242.png\" /></p>\n");
    }

    @Test
    void joplin_hyperlink_to_note_markdown_content_should_render_as_html_anchor() {

        assertMarkdownIsParsedAs(
                "[discuter des demandes ambiguës](:/60fe5fd3db394b09bf2de007de01aa0d)",
                "<p><a href=\":/60fe5fd3db394b09bf2de007de01aa0d\">discuter des demandes ambiguës</a></p>\n");
    }

    //![Screenshot_20200824-183242.png](:/f805dbfc145d4544bec760448298f08b)
    private MarkdownParserPort parserUnderTest() {
        return new FlexmarkMarkdownParserAdapter();
    }


    private void assertMarkdownIsParsedAs(String markdownInput, String expectedHtmlOutput) {
        MarkdownParserPort parser = parserUnderTest();
        var renderedHtml = parser.parse(markdownInput);
        assertThat(renderedHtml).isEqualToNormalizingNewlines(expectedHtmlOutput);
    }
}