package pl.kzochowski.tiktokcrawler.service;

import pl.kzochowski.tiktokcrawler.model.Document;

import java.util.List;

public interface SenderService {

    boolean sendDocuments(List<Document> documentList);

}
