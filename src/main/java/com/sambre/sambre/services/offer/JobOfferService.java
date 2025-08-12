package com.sambre.sambre.services.offer;

import com.sambre.sambre.entities.offers.JobOfferRepository;
import com.sambre.sambre.mapper.offer.JobOfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferMapper jobOfferMapper;


}
