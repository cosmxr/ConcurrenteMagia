package com.example.concurrentemagia.controller;

import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.service.SpellService;
import com.example.concurrentemagia.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador para la página de hechizos
@Controller
@RequestMapping("/spells")
public class SpellController {

    @Autowired
    private SpellService spellService;

    @GetMapping
    public String listSpells(Model model, Authentication authentication) {
        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        List<Spell> spells = spellService.findAllByUserId(userId);
        model.addAttribute("spells", spells);
        return "spells";
    }

    @GetMapping("/new")
    public String newSpellForm(Model model) {
        model.addAttribute("spell", new Spell());
        return "spell-form";
    }

    @PostMapping
    public String saveSpell(@ModelAttribute Spell spell, Authentication authentication) {
        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        spell.setUserId(userId != null ? userId : 0L);
        spellService.save(spell);
        return "redirect:/spells";
    }

    @GetMapping("/delete/{id}")
    public String deleteSpell(@PathVariable Long id) {
        spellService.deleteById(id);
        return "redirect:/spells";
    }
}