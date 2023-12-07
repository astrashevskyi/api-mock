/*
 * Hitachi Confidential
 * All Rights Reserved. Copyright (C) 2023, Hitachi, Ltd.
 */

package org.no;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class SampleApp {
	public static void main(String[] args) {
		SpringApplication.run(SampleApp.class, args);
	}
}
