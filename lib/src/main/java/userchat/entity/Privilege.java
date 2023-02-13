package userchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;
import userchat.views.UserViews;

@Entity
@Table(name = "privilege")
@NoArgsConstructor
@Data
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({ UserViews.CreateUserView.class})
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView({ UserViews.CreateUserView.class})
    private String name;

}