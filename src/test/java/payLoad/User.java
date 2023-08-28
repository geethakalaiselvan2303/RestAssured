package payLoad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	 int id;
	 String username;
	 String firstname;
	 String lastname;
	 String email;
	 String password;
	 String phone;
	 int userStatus=0;
}
