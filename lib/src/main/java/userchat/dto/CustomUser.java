package userchat.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import userchat.entity.Privilege;
import userchat.entity.User;

public class CustomUser implements UserDetails {

	private static final long serialVersionUID = -7730726925333886256L;
	private User user;

    public CustomUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Privilege privilege : user.getPrivileges()) {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return authorities;
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
    
}
