package com.ruanzerah.skyio.domain.mail;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@PropertySources({
    @PropertySource("classpath:application-dev.properties"),
    @PropertySource("classpath:application-prod.properties")
})
public class EmailContent{

    @NotBlank
    private String to;

    @Value("${spring.mail.addresses.from}")
    private String from;

    @Value("${spring.mail.addresses.replyTo}")
    private String replyTo;
    @NotBlank
    private String subject;
    private String text;

}
