package pl.kzochowski.tiktokcrawler.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Document;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.service.DocumentTransformer;

@Slf4j
@Service
public class DocumentTransformerImpl implements DocumentTransformer {

    @Override
    public Document translateToDocument(Post post) {

        //todo implement, use lib
        return new Document();
    }

}
