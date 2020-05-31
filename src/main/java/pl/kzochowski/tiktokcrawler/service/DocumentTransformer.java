package pl.kzochowski.tiktokcrawler.service;


import pl.kzochowski.model.Document;
import pl.kzochowski.tiktokcrawler.model.Post;

public interface DocumentTransformer {
    Document translateToDocument(Post post);
}
