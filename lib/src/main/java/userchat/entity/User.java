package userchat.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;
import userchat.views.UserViews;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	@JsonView({ UserViews.CreateUserView.class})
	private String name;

	@Column(name = "phone_number")
	@JsonView({ UserViews.CreateUserView.class, UserViews.CreateTokenView.class })
	private String phoneNumber;

	@Column(name = "password")
	@JsonView({ UserViews.CreateUserView.class, UserViews.CreateTokenView.class })
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<ChatDetails> chatDetail;
	
	@ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(name = "users_privileges", 
      joinColumns = 
        @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = 
        @JoinColumn(name = "privilege_id", referencedColumnName = "id")) 
	@JsonView({ UserViews.CreateUserView.class})
    private Set<Privilege> privileges;	
}
