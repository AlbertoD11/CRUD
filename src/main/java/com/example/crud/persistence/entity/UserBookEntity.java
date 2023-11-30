package com.example.crud.persistence.entity;

import jakarta.persistence.*;

;

@Entity
@Table(name = "usuario_libro")
public class UserBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long userId;

    @Column(name = "libro_id")
    private Long bookId;

    public UserBookEntity(Long id, Long userId, Long bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public UserBookEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return userId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.userId = usuarioId;
    }

    public Long getLibroId() {
        return bookId;
    }

    public void setLibroId(Long libroId) {
        this.bookId = libroId;
    }
}