package com.example.demo.service;

import com.example.demo.model.Vote;
import com.example.demo.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote){
        return voteRepository.save(vote);
    }
}
