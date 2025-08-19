package com.sambre.sambre.dtos.utils;

import jakarta.validation.constraints.NotBlank;

public record SocialRequest(
        @NotBlank String platform,
        @NotBlank String url
) {
}
