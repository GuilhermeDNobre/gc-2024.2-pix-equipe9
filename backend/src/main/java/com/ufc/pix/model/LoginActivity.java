package com.ufc.pix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "login_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String ip_address;

    @Column(nullable = false, length = 255)
    private String city;

    @Column(nullable = false, length = 255)
    private String region;

    @Column(nullable = false, length = 255)
    private String country;

    @Column(nullable = false, length = 255)
    private String org;

    @Column(nullable = false, length = 255)
    private String postal;

    @Column(nullable = false, length = 255)
    private String timezone;

    @Column(nullable = false)
    private LocalDateTime login_at;

    public LoginActivity(User user, String ipAddress, Map<String, Object> locationData) {
        this.user = user;
        this.ip_address = ipAddress;
        this.city = (String) locationData.get("city");
        this.region = (String) locationData.get("region");
        this.country = (String) locationData.get("country");
        this.org = (String) locationData.get("org");
        this.postal = (String) locationData.get("postal");
        this.timezone = (String) locationData.get("timezone");
        this.login_at = LocalDateTime.now();
    }
}
