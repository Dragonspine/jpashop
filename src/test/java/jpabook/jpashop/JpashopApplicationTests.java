package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpashopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void lombok() {
		Hello hello = new Hello();
		hello.setData("Hello");
		String data = hello.getData();
		assertThat(data).isEqualTo("Hello");
	}
}
