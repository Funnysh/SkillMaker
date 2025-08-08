package com.frantisek.herynek.SkillMaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.frantisek.herynek.SkillMaker")
public class SkillMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillMakerApplication.class, args);
	}

}
