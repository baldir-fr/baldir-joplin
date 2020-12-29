import fr.baldir.joplin.FlexmarkMarkdownParserAdapter;
import fr.baldir.joplin.FrontmatterMetadata;
import fr.baldir.joplin.ports.in.MarkdownParserPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FlexmarkMarkdownFrontmatterParserAdapterTest {

    @Test
    void frontmatter_with_title() {
        // "date: \"2018-12-25\"\n" +
        //                        "tags: [\"tag A\", \"tag B\", \"tag C\"]\n" +
        //                        "excerpt: \"description\"\n" +
        assertYamlFrontmatterIsParsedAs(
                "---\n" +
                        "title: This is a title\n" +
                        "---",
                FrontmatterMetadata.builder()
                        .title("This is a title")
                        .build());
    }

    @Test
    void frontmatter_with_date() {
        // "date: \"2018-12-25\"\n" +
        //                        "tags: [\"tag A\", \"tag B\", \"tag C\"]\n" +
        //                        "excerpt: \"description\"\n" +
        assertYamlFrontmatterIsParsedAs(
                "---\n" +
                        "date: 2018-12-25\n" +
                        "---",
                FrontmatterMetadata.builder()
                        .date("2018-12-25")
                        .build());
    }

    private MarkdownParserPort parserUnderTest() {
        return new FlexmarkMarkdownParserAdapter();
    }


    private void assertYamlFrontmatterIsParsedAs(String markdownInput, FrontmatterMetadata expected) {
        MarkdownParserPort parser = parserUnderTest();
        FrontmatterMetadata renderedHtml = parser.parseFrontmatter(markdownInput);
        assertThat(renderedHtml).isEqualTo(expected);
    }
}