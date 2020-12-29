package fr.baldir.joplin.unit;

import fr.baldir.joplin.usecase.ConvertMarkdownToHtml;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParseMarkdownFileTest {

    @Test
    void empty_markdown_content_should_return_empty_html(){
        String emptyMarkdownContent="";
        String htmlProduced = new ConvertMarkdownToHtml().handle(emptyMarkdownContent);
        assertThat(htmlProduced).isEqualTo("<html><body></body></html>");
    }
}
