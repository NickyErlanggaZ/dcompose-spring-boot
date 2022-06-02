package com.nickyerlangga.resttemplate.service;

import com.nickyerlangga.resttemplate.model.LinkAja;
import com.nickyerlangga.resttemplate.repository.LinkAjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkAjaService {
    @Autowired
    LinkAjaRepository linkAjaRepository;

    public void addLinkAja(LinkAja data) {
        linkAjaRepository.save(data);
    }
}
