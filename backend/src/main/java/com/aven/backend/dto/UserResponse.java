package com.aven.backend.dto;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        Long githubId,
        String githubUsername,
        String displayName,
        String avatarUrl
) {
}
