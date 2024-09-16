package health.mykare.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "user")
@Entity(name = "User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name = "password", nullable = false)
    private String password;
}
