package org.hello.itemservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void hello() {
        String hello = messageSource.getMessage("hello", null, null);
        assertThat(hello).isEqualTo("hello");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessage() {
        String message = messageSource.getMessage("no_code", null, "기본 메세지", null);
        assertThat(message).isEqualTo("기본 메세지");
    }

    @Test
    void argMessage() {
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("hello Spring");
    }

    @Test
    void defaultLang() {
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("hello");
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hola");
    }
}
