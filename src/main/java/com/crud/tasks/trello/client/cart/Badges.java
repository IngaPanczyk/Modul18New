package com.crud.tasks.trello.client.cart;

import com.crud.tasks.trello.client.cart.AttachmentsByType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Badges {
    private int votes;
    private AttachmentsByType attachmentsByType;
}
