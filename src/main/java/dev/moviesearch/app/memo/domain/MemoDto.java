package dev.moviesearch.app.memo.domain;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemoDto {

    @Parameter
    @Schema(description = "메모 아이디")
    private Integer idx;

    @Parameter
    @Schema(description = "메모 내용")
    private String contents;
}
