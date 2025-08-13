package energy.forecaster.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Main office, Break room

    private String address;
    private String city;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY) // improves performance, delays loading entity
    @JoinColumn(name = "user_id",nullable = false)
    @JsonBackReference // prevents infinite recursion from parent side (this class)
    private User user;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Device> devices;

}
