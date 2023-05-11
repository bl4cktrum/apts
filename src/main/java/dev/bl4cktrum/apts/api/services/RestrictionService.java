package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.repositories.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestrictionService {
    private final RestrictionRepository restrictionRepository;

}
