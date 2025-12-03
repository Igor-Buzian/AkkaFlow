package com.example.Akka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*Akka — это фреймворк для обработки миллионов сообщений. Акторы не привязаны к потокам:
Dispatcher выбирает свободный поток для обработки каждого сообщения.
Актор живёт долго и может обрабатывать любое количество сообщений одно за другим.
Race conditions не происходят, потому что каждый актор обрабатывает только одно сообщение одновременно, а mailbox гарантирует порядок.
Сообщение передаётся актору → актор его обрабатывает → tell(next) отправляет сообщение следующему актору.
Потоки используются для распределения обработки между акторными сообщениями, а mailbox обеспечивает безопасную последовательность.*/
@SpringBootApplication
public class AkkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkaApplication.class, args);
	}

}
