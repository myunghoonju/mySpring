package org.hello.springmvc.web.basic;

import lombok.RequiredArgsConstructor;
import org.hello.springmvc.domain.item.Item;
import org.hello.springmvc.domain.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("items", itemList);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = Item.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        // @ModelAttribute("item")가 아래를 생성해준다. 지정한 "item"으로 key만듬.
       // model.addAttribute("item", item);

        return "basic/item";
    }

    //PRG (post, redirect, get :: solve refresh problem)
    @PostMapping("/add") //Post ->
    public String addItemV3(@ModelAttribute Item item) { // @ModelAttribute()지정안하면 Item 클래스 첫글자 소문자로 치환하여 처리된다.
        itemRepository.save(item);
        return "redirect:/basic/items" + item.getId(); //Redirect -> Get
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) { // @ModelAttribute생략가능.
        itemRepository.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String updateForm(@PathVariable Long itemId, @ModelAttribute("item") Item updateitem) {
        itemRepository.update(itemId, updateitem);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * test db setting
     */
    @PostConstruct
    public void init() {
        Item itemA = Item.builder()
                .itemName("itemA")
                .price(10_000)
                .quantity(10)
                .build();

        Item itemB = Item.builder()
                .itemName("itemB")
                .price(20_000)
                .quantity(20)
                .build();

        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }
}
