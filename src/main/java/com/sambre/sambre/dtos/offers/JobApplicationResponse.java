package com.sambre.sambre.dtos.offers;

public record JobApplicationResponse(
        String id,
        String motivation,
        String applicationDate,
        String status,

        // 🔹 Infos simplifiées
        String candidateId,
        String candidateName,
        String jobOfferId,
        String jobOfferTitle
) {
}
