package com.example.concurrentemagia.controller;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.service.MagicalEventService;
import com.example.concurrentemagia.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class MagicalEventController {

    @Autowired
    private MagicalEventService magicalEventService;

    @Autowired
    private SpellService spellService;

    @GetMapping("/new")
    public String newEventForm(Model model) {
        model.addAttribute("event", new MagicalEvent());
        model.addAttribute("spells", spellService.findAll());
        return "event-form";
    }

    @PostMapping
    public String saveEvent(@ModelAttribute MagicalEvent event) {
        magicalEventService.save(event);
        return "redirect:/events";
    }

    @GetMapping("/challenge")
    public String challenge(Model model) {
        model.addAttribute("event", magicalEventService.getCurrentEvent());
        model.addAttribute("spells", spellService.findAll());
        return "challenge";
    }

    @PostMapping("/useSpell")
    public String useSpell(@RequestParam Long spellId, Model model) {
        Spell spell = spellService.findById(spellId);
        MagicalEvent event = magicalEventService.getCurrentEvent();
        magicalEventService.applySpell(event, spell);
        String result = magicalEventService.applySpell(event, spell);

        model.addAttribute("event", event);
        model.addAttribute("spells", magicalEventService.getAllSpells());

        switch (result) {
            case "victory":
                model.addAttribute("message", "Has Ganado!");
                return "victory";
            case "defeat":
                model.addAttribute("message", "Has sido derrotado!");
                return "defeat";
            case "finalVictory":
                model.addAttribute("message","Has ganado al final Boss!!");
                return "final.Victory";

            default:
                return "challenge";
        }
    }
}