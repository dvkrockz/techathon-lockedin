package com.techathon.lockedin.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techathon.lockedin.models.UserDetails;

@Repository
public   interface UserRepository extends JpaRepository<UserDetails, Long> {
	
	
	Optional<UserDetails> findByGitHubUserName(String userName);

}
