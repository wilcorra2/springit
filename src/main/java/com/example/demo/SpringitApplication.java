package com.example.demo;

import com.example.demo.config.SpringitProperties;
import com.example.demo.model.Comment;
import com.example.demo.model.Link;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LinkRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@SpringBootApplication
//@EnableConfigurationProperties(SpringitProperties.class)
//@EnableJpaAuditing
@EnableTransactionManagement
public class SpringitApplication {

	//@Autowired
	//private SpringitProperties springitProperties;
    private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
		//System.out.println("Hello, world!");
	}

	@Bean
	PrettyTime prettyTime(){
		return new PrettyTime();
	}

	// TODO * Configuring this bean should not be needed once Spring Boot's Thymeleaf starter includes configuration
// TODO   for thymeleaf-extras-springsecurity5 (instead of thymeleaf-extras-springsecurity4)
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	/*@Bean
	//@Profile("dev")
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
		return args -> {
			Link link = new Link("Getting Started with Spring Boot 2", "http://therealdanvega.com/spring-boot-2");
			linkRepository.save(link);

			Comment comment = new Comment("Comment test", link);
			commentRepository.save(comment);
			link.addComment(comment);
		};
	}*/

}
