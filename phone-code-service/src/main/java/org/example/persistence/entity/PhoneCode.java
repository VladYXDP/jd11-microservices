package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "phone_codes")
public class PhoneCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneCode phoneCode)) return false;
        return Objects.equals(id, phoneCode.id) && Objects.equals(code, phoneCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "id = " + id + ", " +
                "code = " + code + ", " +
                "}";
    }
}

