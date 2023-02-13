package userchat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import userchat.entity.ChatDetails;

public interface ChatDetailsRespository  extends JpaRepository<ChatDetails, String>, JpaSpecificationExecutor<ChatDetails> {

}
