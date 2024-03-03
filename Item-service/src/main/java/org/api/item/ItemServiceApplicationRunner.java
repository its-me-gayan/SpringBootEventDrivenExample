package org.api.item;

import org.api.item.entity.Item;
import org.api.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ItemServiceApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplicationRunner.class, args);
    }

    @Autowired
    private ItemRepository itemRepository;
    @EventListener(ApplicationReadyEvent.class)
    private void executeInitialData(){
        Random r = new Random();
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < 10; i++) {
            double randomValue = 100 + (1000 - 100) * r.nextDouble();
            String string = UUID.randomUUID().toString();
            Item item = Item.builder().itemName("item_" + string)
                    .category("ITEM")
                    .price(Double.parseDouble(df.format(randomValue)))
                    .itemDescription("des_" + string)
                    .updatedAt(new Date(System.currentTimeMillis()))
                    .createdAt(new Date(System.currentTimeMillis())).build();
            itemRepository.save(item);
        }
    }
}