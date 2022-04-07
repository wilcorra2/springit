package com.example.demo.controller;


import com.example.demo.model.Link;
import com.example.demo.model.Vote;
import com.example.demo.repository.LinkRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.service.LinkService;
import com.example.demo.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteService voteService;
    private LinkService  linkService;

    public VoteController(VoteService voteService, LinkService  linkService) {
        this.voteService = voteService;
        this.linkService = linkService;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount){
        Optional<Link> optionalLink = linkService.findById(linkID);
        if (optionalLink.isPresent()){
            Link link = optionalLink.get();
            Vote vote = new Vote(direction, link);
            voteService.save(vote);

            int updatedVoteCount = voteCount + direction;

            link.setVoteCount(updatedVoteCount);
            linkService.save(link);

            return updatedVoteCount;
        }

        return voteCount;
    }
}

