package com.applr.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Chat {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String chat_name;
	private String chat_image;
	private LocalDateTime timestamp;
	@ManyToMany
	private List<User> users = new ArrayList<>();
	@OneToMany(mappedBy = "chat")
	private List<Message> messages = new ArrayList<>();
}
