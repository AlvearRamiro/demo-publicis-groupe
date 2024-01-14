package com.test.publicis.groupe.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.publicis.groupe.demo.processor.ProcesadorPlanillas;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	ProcesadorPlanillas procesadorPlanillas;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// To do here...
		System.out.println("total=" + procesadorPlanillas.total("7910976448"));
	}
}
