package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.Link;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LinkRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;


@Controller
//@RequestMapping("/links")
public class LinkController {

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    private LinkService linkService;
    private CommentService commentService;

    public LinkController(LinkService linkService,CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("links", linkService.findAll());
        return "link/list";
    }

    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkService.findById(id);
        if(link.isPresent()){
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);
            model.addAttribute("comment", comment);
            model.addAttribute("link",currentLink);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            logger.info("Errors were found while submitting a link");
            model.addAttribute("link", link);
            return "link/submit";
        }
        else {
            linkService.save(link);
            logger.info("New link was saved.");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect: /link/{id};";
        }
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.info("There was an error adding new comment.");
        }
        else {
            commentService.save(comment);
            logger.info("New comment was added successfully.");
        }
        return "redirect:/link/"+comment.getLink().getId();
    }
    /*

    // list
    @GetMapping("/")
    public List<Link> list() {
        return linkRepository.findAll();
    }

    // CRUD
    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    @GetMapping("/{id}")
    public Optional<Link> read(@PathVariable Long id) {
        return linkRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        linkRepository.deleteById(id);

    }*/
}
