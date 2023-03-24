package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.model.PizzaOrder;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
@Component
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // Fix the getPizza method below in line 49.
        pizzaOrder.setPizza(getPizza(pizzaId));
        System.out.println(pizzaOrder);

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }


    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId,@ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder) {

        // Save the order
        pizzaOrder.setPizza(getPizza(pizzaId));
        System.out.println("asdasdasdas");
        return "redirect:/home";
    }

    //TODO
    private Pizza getPizza(UUID pizzaId) {
        // Get the pizza from repository based on it's id

        return pizzaRepository.readAll().stream()
                .filter(a->a.getId().equals(pizzaId)).findAny().get();
    }

}
