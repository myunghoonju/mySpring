package org.hello.springmvc.domain.item;

import org.hello.itemservice.domain.Item;
import org.hello.itemservice.domain.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    @DisplayName("save test")
    void save() {
        //given
        Item item = Item.builder()
                .itemName("itemA")
                .price(10_000)
                .quantity(10)
                .build();
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item foundItem = itemRepository.findById(savedItem.getId());

        assertThat(foundItem).isEqualTo(savedItem);

    }

    @Test
    @DisplayName("find all test")
    void findAll() {
        //given
        Item item1 = Item.builder()
                .itemName("itemA")
                .price(10_000)
                .quantity(10)
                .build();
        Item item2 = Item.builder()
                .itemName("itemB")
                .price(20_000)
                .quantity(20)
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);

    }
}