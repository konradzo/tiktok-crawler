package pl.kzochowski.tiktokcrawler.integration.transform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.stereotype.Component;
import pl.kzochowski.model.Document;
import pl.kzochowski.tiktokcrawler.model.PagePostsDto;
import pl.kzochowski.tiktokcrawler.service.DocumentTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PostTransformer extends AbstractPayloadTransformer<PagePostsDto, List<Document>> {

    private final DocumentTransformer documentTransformer;

    public PostTransformer(DocumentTransformer documentTransformer) {
        this.documentTransformer = documentTransformer;
    }

    @Override
    protected List<Document> transformPayload(PagePostsDto pagePostsDto) {
        log.info("Transforming post list...");
        return pagePostsDto.getPostList().stream()
                .map(documentTransformer::translateToDocument)
                .collect(Collectors.toList());
    }

}
