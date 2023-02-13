package userchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "chat_details")
@NoArgsConstructor
@Data
public class ChatDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@Column(name = "chat_blob_data")
	private String chatBlobData;
	
	@Column(name = "payment_amount")
	private float paymentAmount;
	
	@Column(name = "time_spent")
	private float timeSpent;
	
	@Column(name = "chat_status")
	private String chatStatus;

}
