package ru.shlokov.UserRequest.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shlokov.UserRequest.model.converter.RequestStateConverter;
import ru.shlokov.UserRequest.model.enums.RequestState;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Shlokov Andrey
 */
@Data
@Entity
@Table(name = "requests")
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "state")
    @Convert(converter = RequestStateConverter.class)
    private RequestState state = RequestState.draft;
    @Column(name = "text")
    private String text;
    @Column(name = "date_of_creation")
    private final LocalDateTime dateOfCreation = LocalDateTime.now();
    @Column (name = "author")
    @JoinColumn(referencedColumnName = "email")
    private String author;

    public Request(String text, String user) {
        this.text = text;
        this.author = user;
    }
}
