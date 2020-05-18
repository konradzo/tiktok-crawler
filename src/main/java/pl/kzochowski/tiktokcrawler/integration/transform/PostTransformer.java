package pl.kzochowski.tiktokcrawler.integration.transform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.stereotype.Component;
import pl.kzochowski.tiktokcrawler.model.Document;
import pl.kzochowski.tiktokcrawler.model.PagePostsDto;

import java.util.List;

@Slf4j
@Component
public class PostTransformer extends AbstractPayloadTransformer<PagePostsDto, List<Document>> {



    @Override
    protected List<Document> transformPayload(PagePostsDto pagePostsDto) {

        //todo implement, use custom lib
        return null;
    }

}
