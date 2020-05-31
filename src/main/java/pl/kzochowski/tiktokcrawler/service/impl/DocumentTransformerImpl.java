package pl.kzochowski.tiktokcrawler.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pl.kzochowski.model.*;
import pl.kzochowski.tiktokcrawler.model.Post;
import pl.kzochowski.tiktokcrawler.service.DocumentTransformer;
import pl.kzochowski.tiktokcrawler.service.LanguageService;

import java.util.Date;

@Slf4j
@Service
public class DocumentTransformerImpl implements DocumentTransformer {
    private final LanguageService languageService;

    public DocumentTransformerImpl(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public Document translateToDocument(final Post post) {
        String language = languageService.resolveLanguage(post.getMessage());
        return Document.builder()
                .author(Author
                        .builder()
                        .id(post.getUserId())
                        .nickname(post.getUniqueId())
                        .language(language)
                        .name(post.getUniqueId())
                        .build())
                .content(Content.builder()
                        .body(post.getMessage())
                        .header("")
                        .video("")
                        .build())
                .lang(language)
                .group("")
                .createAt(new Date())
                .timestamp(Date.from(post.getTimestamp().toInstant()))
                .source(Source
                        .builder()
                        .sourceId(Long.parseLong(post.getUserId()))
                        .build())
                .metadata(Metadata
                        .builder()
                        .shared(post.getShares())
                        .comments(post.getComments())
                        .likes(post.getLikes())
                        .view(post.getViews())
                        .build())
                .build();
    }

}
