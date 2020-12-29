package fr.baldir.joplin.ports.in;

import fr.baldir.joplin.FrontmatterMetadata;

public interface MarkdownParserPort {

    String parse(String markdownContent);

    FrontmatterMetadata parseFrontmatter(String markdownContent);
}
