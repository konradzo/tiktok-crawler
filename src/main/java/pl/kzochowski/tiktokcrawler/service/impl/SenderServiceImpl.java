package pl.kzochowski.tiktokcrawler.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.tiktokcrawler.model.Document;
import pl.kzochowski.tiktokcrawler.service.SenderService;

import java.util.List;

@Slf4j
@Service
public class SenderServiceImpl implements SenderService {

    @Override
    public boolean sendDocuments(List<Document> documentList) {
        return false;
    }

}
