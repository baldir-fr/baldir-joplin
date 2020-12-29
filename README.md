# baldir-joplin-generators

## Use cases

### Convert joplin notes into low-tech html

I want to re-use my existing joplin note and convert them into a static low-tech web site that can be hosted anywhere, 
even as simple standalone html.

1. Given a markdown text, I want to convert it to HTML
2. Given a Joplin Notebook, I want to convert it as a website
3. Given a Joplin Notebook, I want to convert it as a with hyperlinks
4. Given a Joplin Notebook, I want to convert it as a with resources

### Extensions

Joplin holds resources internaly. It can be note or attachment.

Joplin resource image link to attachment
```md
![Screenshot_20200824-183242.png](:/f805dbfc145d4544bec760448298f08b)
```

Joplin note hyperlink
```md
- [discuter des demandes ambiguës](:/60fe5fd3db394b09bf2de007de01aa0d)
```

First, raw markdown will be parsed to Html.
Then, Html can be post-processed so joplin resources URIs will be swapped with target html files.