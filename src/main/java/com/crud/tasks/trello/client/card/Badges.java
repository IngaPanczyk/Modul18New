package com.crud.tasks.trello.client.card;

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
