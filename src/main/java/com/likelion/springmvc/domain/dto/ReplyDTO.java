package com.likelion.springmvc.domain.dto;

import com.likelion.springmvc.domain.entity.Reply;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyDTO {
    private Long id;
    private String author;
    private String content;

    public Reply toEntity() {
        return new Reply(id, author, content);
    }
}
