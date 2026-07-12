package com.pc.journalApp.dto;

import com.pc.journalApp.enums.Sentiment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JournalRequest {

    @Schema(example = "My First Journal")
    @NotBlank
    private String title;

    @Schema(example = "Today was a productive day.")
    @NotBlank
    private String content;

    @Schema(example = "HAPPY")
    @NotNull
    private Sentiment sentiment;

}