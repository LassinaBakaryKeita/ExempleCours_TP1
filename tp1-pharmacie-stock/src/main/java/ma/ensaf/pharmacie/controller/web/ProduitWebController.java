package ma.ensaf.pharmacie.controller.web;

import ma.ensaf.pharmacie.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProduitWebController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/produits")
    public String listProduits(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "produits";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/produits";
    }
}
