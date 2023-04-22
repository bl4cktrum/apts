package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.UserCreateRequest;
import dev.bl4cktrum.apts.api.repositories.RelevantRepository;
import org.springframework.stereotype.Service;

@Service
public class RelevantService {
    private final RelevantRepository relevantRepository;

    public RelevantService(RelevantRepository relevantRepository) {
        this.relevantRepository = relevantRepository;
    }

    public Relevant createUser(UserCreateRequest userCreateRequest){
        Relevant test = Relevant.builder().email(userCreateRequest.email).name(userCreateRequest.name).build();
        System.out.println(test);
        return relevantRepository.save(test);
    }
}
